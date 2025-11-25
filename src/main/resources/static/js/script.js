// Configuration for all 54 Functions
const CONFIG = {
    array: [
            {
                id: 'min',
                name: 'Min Element',
                desc: 'Input: Space-separated integers (e.g., "1 5 -2 8").\nOutput: The smallest integer in the array.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'max',
                name: 'Max Element',
                desc: 'Input: Space-separated integers (e.g., "1 5 -2 8").\nOutput: The largest integer in the array.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'mergeSort',
                name: 'Merge Sort',
                desc: 'Input: Unsorted space-separated integers.\nOutput: The array sorted in ascending order.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'twoSumUniquePairs',
                name: '2Sum Unique Pairs',
                desc: 'Input: An integer array and a target sum.\nOutput: The count of unique pairs (i, j) that add up to the target.',
                inputs: [{l:'Array Elements', k:'arr'}, {l:'Target Sum', k:'val'}]
            },
            {
                id: 'majorityElement',
                name: 'Majority Element',
                desc: 'Input: Space-separated integers.\nOutput: The element appearing > N/2 times, or Min Value if none exists.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'longestSubarrayWithSum',
                name: 'Longest Subarray (Sum)',
                desc: 'Input: An array and a target sum.\nOutput: The length of the longest contiguous subarray summing to the target.',
                inputs: [{l:'Array Elements', k:'arr'}, {l:'Target Sum', k:'val'}]
            },
            {
                id: 'maxProductSubarray',
                name: 'Max Product Subarray',
                desc: 'Input: Space-separated integers.\nOutput: The maximum product obtainable from a contiguous subarray.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'nextPermutation',
                name: 'Next Permutation',
                desc: 'Input: An array of digits/numbers.\nOutput: The lexicographically next greater permutation of numbers.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'rotate',
                name: 'Rotate Array',
                desc: 'Input: An array and a number of steps (k).\nOutput: The array rotated to the right by k steps.',
                inputs: [{l:'Array Elements', k:'arr'}, {l:'Steps (k)', k:'val'}]
            },
            {
                id: 'hasIntersection',
                name: 'Has Intersection',
                desc: 'Input: Two sorted arrays.\nOutput: "true" if they share at least one common element, "false" otherwise.',
                inputs: [{l:'Array 1 (Sorted)', k:'arr'}, {l:'Array 2 (Sorted)', k:'arr2'}]
            },
            {
                id: 'isSorted',
                name: 'Is Sorted',
                desc: 'Input: Space-separated integers.\nOutput: "true" if ascending sorted, "false" otherwise.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'binarySearchFirstOccurrence',
                name: 'Binary Search (First)',
                desc: 'Input: A sorted array and a target value.\nOutput: The index of the first occurrence of the target, or -1.',
                inputs: [{l:'Sorted Array', k:'arr'}, {l:'Target Value', k:'val'}]
            }
        ],
    string: [
            {
                id: 'reverse',
                name: 'Reverse String',
                desc: 'Input: A string (e.g. "Hello World").\nOutput: The string reversed (e.g. "dlroW olleH").',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'isPalindrome',
                name: 'Is Palindrome',
                desc: 'Input: A string.\nOutput: "True" if the string reads the same forwards and backwards (case-insensitive).',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'longestSubstringWithoutRepeatingCharacters',
                name: 'Longest Unique Substr',
                desc: 'Input: A string.\nOutput: The length of the longest substring containing only unique characters.',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'isAnagram',
                name: 'Is Anagram',
                desc: 'Input: Two strings.\nOutput: "True" if both strings contain the exact same characters in any order.',
                inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}]
            },
            {
                id: 'countOccurrences',
                name: 'Count Occurrences',
                desc: 'Input: A source string and a target substring.\nOutput: The number of times the target appears in the source.',
                inputs: [{l:'Source String', k:'s1'}, {l:'Target Substring', k:'s2'}]
            },
            {
                id: 'toTitleCase',
                name: 'Title Case',
                desc: 'Input: A sentence.\nOutput: The sentence with the first letter of every word capitalized.',
                inputs: [{l:'Sentence', k:'s1'}]
            },
            {
                id: 'countUniqueWords',
                name: 'Count Unique Words',
                desc: 'Input: A sentence.\nOutput: The count of unique words (case-insensitive, ignores punctuation).',
                inputs: [{l:'Sentence', k:'s1'}]
            },
            {
                id: 'cleanWhitespace',
                name: 'Clean Whitespace',
                desc: 'Input: A string with irregular spacing.\nOutput: The string with leading/trailing spaces removed and internal spaces consolidated.',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'splitByLength',
                name: 'Split By Length',
                desc: 'Input: String, delimiter, and min length.\nOutput: Array of substrings split by delimiter, excluding parts shorter than min length.',
                inputs: [{l:'String', k:'s1'}, {l:'Delimiter', k:'s2'}, {l:'Min Length', k:'val'}]
            },
            {
                id: 'removeDuplicateChars',
                name: 'Remove Dup Chars',
                desc: 'Input: A string.\nOutput: The string with all duplicate characters removed (preserving first occurrence).',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'isAlphabetic',
                name: 'Is Alphabetic',
                desc: 'Input: A string.\nOutput: "True" if the string contains ONLY letters (A-Z, a-z), "False" otherwise.',
                inputs: [{l:'String', k:'s1'}]
            }
        ],
    graph: [
            {
                id: 'shortestPathBFS',
                name: 'Shortest Path (BFS)',
                desc: 'Input: Number of nodes, unweighted edges (format "u v" per line), and a start node.\nOutput: Array of shortest distances from the start node to all other nodes (-1 if unreachable).',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}]
            },
            {
                id: 'traverseDFS',
                name: 'Traverse DFS',
                desc: 'Input: Number of nodes, edges, and a start node.\nOutput: A list of nodes in the order they were visited during Depth-First Search.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}]
            },
            {
                id: 'containsCycleUndirected',
                name: 'Has Cycle',
                desc: 'Input: Number of nodes and undirected edges.\nOutput: "True" if the graph contains at least one cycle, "False" otherwise.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            },
            {
                id: 'maxDegree',
                name: 'Max Degree',
                desc: 'Input: Number of nodes and edges.\nOutput: The highest number of connections (degree) attached to any single node.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            },
            {
                id: 'topologicalSortKahn',
                name: 'Topological Sort',
                desc: 'Input: Number of nodes and directed edges (DAG).\nOutput: A linear ordering of vertices (Topological Sort), or empty list if a cycle exists.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            },
            {
                id: 'isTree',
                name: 'Is Tree',
                desc: 'Input: Number of nodes and undirected edges.\nOutput: "True" if the graph is fully connected and has no cycles (i.e., is a Tree).',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            },
            {
                id: 'countConnectedComponents',
                name: 'Connected Components',
                desc: 'Input: Number of nodes and undirected edges.\nOutput: The number of disjoint connected sub-graphs.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            },
            {
                id: 'shortestPathDijkstra',
                name: 'Dijkstra',
                desc: 'Input: Number of nodes, WEIGHTED edges (format "u v w" per line), and a start node.\nOutput: Array of shortest path distances respecting edge weights.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v w per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}]
            },
            {
                id: 'isSinkNode',
                name: 'Is Sink Node',
                desc: 'Input: Number of nodes, edges, and a specific node to check.\nOutput: "True" if the node has zero outgoing edges.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Check Node', k:'val2'}]
            },
            {
                id: 'computeIndegrees',
                name: 'Compute Indegrees',
                desc: 'Input: Number of nodes and directed edges.\nOutput: An array where index i represents the number of incoming edges to node i.',
                inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}]
            }
        ],
    dp: [
            {
                id: 'countSubsetsWithSumK',
                name: 'Count Subsets (Sum K)',
                desc: 'Input: An array of non-negative integers and a target sum K.\nOutput: The number of subsets whose elements sum up to K.',
                inputs: [{l:'Array Elements', k:'arr'}, {l:'Target K', k:'val'}]
            },
            {
                id: 'countPartitionsWithGivenDifference',
                name: 'Count Partitions (Diff)',
                desc: 'Input: An array and a difference value.\nOutput: The number of ways to partition the array into two subsets such that (Sum_S1 - Sum_S2) = Diff.',
                inputs: [{l:'Array Elements', k:'arr'}, {l:'Difference', k:'val'}]
            },
            {
                id: 'maxSumNonAdjacent',
                name: 'Max Sum Non-Adj',
                desc: 'Input: An array of non-negative integers.\nOutput: The maximum sum possible by selecting elements that are not adjacent to each other.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'longestCommonSubsequence',
                name: 'LCS',
                desc: 'Input: Two strings.\nOutput: The longest sequence of characters that appear in both strings in the same relative order (not necessarily contiguous).',
                inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}]
            },
            {
                id: 'longestPalindromicSubsequence',
                name: 'LPS',
                desc: 'Input: A string.\nOutput: The longest subsequence within the string that reads the same forwards and backwards.',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'longestCommonSubstring',
                name: 'Longest Common Substr',
                desc: 'Input: Two strings.\nOutput: The longest continuous substring present in both strings.',
                inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}]
            },
            {
                id: 'minInsertionsToMakePalindrome',
                name: 'Min Insertions (Palindrome)',
                desc: 'Input: A string.\nOutput: The minimum number of characters that must be inserted to convert the string into a palindrome.',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'longestIncreasingSubsequence',
                name: 'LIS',
                desc: 'Input: An integer array.\nOutput: One instance of the longest subsequence where elements are in strictly increasing order.',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'minPathSum',
                name: 'Min Path Sum (Grid)',
                desc: 'Input: Grid dimensions and values (comma-separated rows).\nOutput: The minimum sum path from top-left to bottom-right (moving only down or right).',
                inputs: [{l:'Rows', k:'val'}, {l:'Cols', k:'val2'}, {l:'Grid (row per line, comma sep)', k:'s1', type:'area'}]
            }
        ],
    stack: [
            {
                id: 'reverseStack',
                name: 'Reverse Stack',
                desc: 'Input: Space-separated elements (Top-to-Bottom order).\nOutput: The stack with the order of elements reversed.',
                inputs: [{l:'Stack Elements', k:'arr'}]
            },
            {
                id: 'isBalanced',
                name: 'Is Balanced',
                desc: 'Input: A string containing parentheses/brackets like "{[()]}".\nOutput: "True" if symbols are balanced/nested correctly.',
                inputs: [{l:'Expression', k:'s1'}]
            },
            {
                id: 'sortStack',
                name: 'Sort Stack',
                desc: 'Input: Space-separated integers.\nOutput: The stack sorted such that the smallest element is at the top.',
                inputs: [{l:'Stack Elements', k:'arr'}]
            },
            {
                id: 'evaluatePostfix',
                name: 'Evaluate Postfix',
                desc: 'Input: Space-separated Reverse Polish Notation expression (e.g., "2 3 * 1 +").\nOutput: The calculated integer result.',
                inputs: [{l:'Postfix Expression', k:'s1'}]
            },
            {
                id: 'nextGreaterElement',
                name: 'Next Greater Element',
                desc: 'Input: Space-separated integers.\nOutput: An array where each position shows the first larger element to its right (-1 if none).',
                inputs: [{l:'Array Elements', k:'arr'}]
            },
            {
                id: 'longestValidParentheses',
                name: 'Longest Valid Paren',
                desc: 'Input: A string of parentheses (e.g., "(()))").\nOutput: The length of the longest contiguous valid substring.',
                inputs: [{l:'String', k:'s1'}]
            },
            {
                id: 'infixToPostfix',
                name: 'Infix to Postfix',
                desc: 'Input: Standard infix expression (e.g., "A + B * C").\nOutput: The equivalent Postfix string.',
                inputs: [{l:'Infix Expression', k:'s1'}]
            },
            {
                id: 'findMiddleElement',
                name: 'Find Middle',
                desc: 'Input: Space-separated elements.\nOutput: The element located at the middle index of the stack.',
                inputs: [{l:'Stack Elements', k:'arr'}]
            },
            {
                id: 'isPermutation',
                name: 'Is Permutation',
                desc: 'Input: Two stacks of elements.\nOutput: "True" if both stacks contain the exact same elements with the same frequency.',
                inputs: [{l:'Stack 1', k:'arr'}, {l:'Stack 2', k:'arr2'}]
            },
            {
                id: 'removeAllOccurrences',
                name: 'Remove All Occur',
                desc: 'Input: A stack and a target item to remove.\nOutput: The stack with all instances of the target removed (order preserved).',
                inputs: [{l:'Stack Elements', k:'arr'}, {l:'Item to Remove', k:'s1'}]
            }
        ]
};

// Initialize Logic
const params = new URLSearchParams(window.location.search);
const type = params.get('type');
const titleEl = document.getElementById('category-title');
const listEl = document.getElementById('function-list');
const workspace = document.getElementById('function-workspace');
const welcome = document.getElementById('welcome-message');
const inputContainer = document.getElementById('input-container');
let currentFuncId = null;

if (type && CONFIG[type]) {
    titleEl.textContent = type.charAt(0).toUpperCase() + type.slice(1) + " Utils";

    // Render Sidebar
    CONFIG[type].forEach(func => {
        const li = document.createElement('li');
        li.textContent = func.name;
        li.onclick = () => loadFunction(func);
        listEl.appendChild(li);
    });
}

function loadFunction(func) {
    currentFuncId = func.id;
    welcome.style.display = 'none';
    workspace.style.display = 'block';

    // Highlight Active
    document.querySelectorAll('li').forEach(li => li.classList.remove('active'));
    event.target.classList.add('active');

    document.getElementById('func-name').textContent = func.name;
    // Use regex to replace \n with line breaks for display
    document.getElementById('func-desc').innerText = func.desc || ("Input parameters for " + func.name);
    document.getElementById('output-area').textContent = "Waiting for input...";

    // Generate Inputs
    inputContainer.innerHTML = '';
    func.inputs.forEach((input, index) => {
        const div = document.createElement('div');
        div.className = 'input-wrapper';
        div.innerHTML = `<label>${input.l}</label>`;

        if (input.type === 'area') {
            div.innerHTML += `<textarea id="inp-${index}" rows="5"></textarea>`;
        } else {
            div.innerHTML += `<input type="text" id="inp-${index}">`;
        }
        inputContainer.appendChild(div);
    });
}

document.getElementById('run-btn').onclick = async () => {
    if (!currentFuncId) return;

    const funcConfig = CONFIG[type].find(f => f.id === currentFuncId);
    const payload = { category: type, functionName: currentFuncId };

    // Gather Inputs
    funcConfig.inputs.forEach((inp, index) => {
        const val = document.getElementById(`inp-${index}`).value;
        payload[inp.k] = val; // Maps 'arr', 's1', 'val', etc.
    });

    document.getElementById('output-area').textContent = "Processing...";

    try {
        const res = await fetch('/api/execute', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        });
        const data = await res.json();
        document.getElementById('output-area').textContent =
            `Status: ${data.status}\nResult: ${data.result}\n\n[Full Response]: ${JSON.stringify(data, null, 2)}`;
    } catch (e) {
        document.getElementById('output-area').textContent = "Error: " + e.message;
    }
};