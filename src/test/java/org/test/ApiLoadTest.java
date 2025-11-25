package org.test;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Performance/Load Test mimicking Apache JMeter.
 * This test spawns concurrent threads to hammer the API and measure throughput.
 * * REQUIRES: Spring Boot App running on port 8081.
 */
public class ApiLoadTest {

    private static final String API_URL = "http://localhost:8082/api/execute";
    private static final int CONCURRENT_USERS = 10000; // Number of threads
    private static final int REQUESTS_PER_USER = 1; // Requests per thread
    private static final int TOTAL_REQUESTS = CONCURRENT_USERS * REQUESTS_PER_USER;

    // A heavy payload to stress the CPU (Merge Sort on unsorted integers)
    private static final String JSON_PAYLOAD = """
        {
            "category": "array",
            "functionName": "mergeSort",
            "arr": "99 2 45 12 8 1 55 33 22 11 90 4 6 7 10 100 1000 500 250 125 60 30 15 5 1 0"
        }
    """;

    @Test
    public void testApiThroughput() throws InterruptedException {
        System.out.println("=== STARTING LOAD TEST ===");
        System.out.printf("Target: %s%n", API_URL);
        System.out.printf("Users: %d | Requests per User: %d | Total Requests: %d%n",
                CONCURRENT_USERS, REQUESTS_PER_USER, TOTAL_REQUESTS);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        // Create a thread pool to simulate concurrent users
        ExecutorService executor = Executors.newFixedThreadPool(CONCURRENT_USERS);
        HttpClient client = HttpClient.newHttpClient();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < CONCURRENT_USERS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < REQUESTS_PER_USER; j++) {
                    try {
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(API_URL))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(JSON_PAYLOAD))
                                .timeout(Duration.ofSeconds(5))
                                .build();

                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                        if (response.statusCode() == 200 && response.body().contains("SUCCESS")) {
                            successCount.incrementAndGet();
                        } else {
                            failureCount.incrementAndGet();
                            System.err.println("Failed Status: " + response.statusCode());
                        }
                    } catch (Exception e) {
                        failureCount.incrementAndGet();
                        // e.printStackTrace(); // Commented out to keep console clean during stress
                    }
                }
            });
        }

        // Wait for all threads to finish
        executor.shutdown();
        boolean finished = executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("=== LOAD TEST RESULTS ===");
        System.out.printf("Time Taken: %d ms%n", duration);
        System.out.printf("Successful Requests: %d%n", successCount.get());
        System.out.printf("Failed Requests: %d%n", failureCount.get());

        double rps = (double) successCount.get() / (duration / 1000.0);
        System.out.printf("Throughput: %.2f Requests/Second%n", rps);
        System.out.println("=========================");

        // Assertions for Project Requirements
        assertTrue(finished, "Test timed out before completion");
        assertTrue(failureCount.get() == 0, "Expected 0 failures under load");
        assertTrue(rps > 50, "API performance is too low (< 50 RPS)");
    }
}