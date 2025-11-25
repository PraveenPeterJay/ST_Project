// Configuration for all 54 Functions
const CONFIG = {
    array: [
        { id: 'min', name: 'Min Element', inputs: [{l:'Array (e.g. 1 5 -2)', k:'arr'}] },
        { id: 'max', name: 'Max Element', inputs: [{l:'Array', k:'arr'}] },
        { id: 'mergeSort', name: 'Merge Sort', inputs: [{l:'Array', k:'arr'}] },
        { id: 'twoSumUniquePairs', name: '2Sum Unique Pairs', inputs: [{l:'Array', k:'arr'}, {l:'Target Sum', k:'val'}] },
        { id: 'majorityElement', name: 'Majority Element', inputs: [{l:'Array', k:'arr'}] },
        { id: 'longestSubarrayWithSum', name: 'Longest Subarray (Sum)', inputs: [{l:'Array', k:'arr'}, {l:'Target Sum', k:'val'}] },
        { id: 'maxProductSubarray', name: 'Max Product Subarray', inputs: [{l:'Array', k:'arr'}] },
        { id: 'nextPermutation', name: 'Next Permutation', inputs: [{l:'Array', k:'arr'}] },
        { id: 'rotate', name: 'Rotate Array', inputs: [{l:'Array', k:'arr'}, {l:'Steps (k)', k:'val'}] },
        { id: 'hasIntersection', name: 'Has Intersection', inputs: [{l:'Array 1 (Sorted)', k:'arr'}, {l:'Array 2 (Sorted)', k:'arr2'}] },
        { id: 'isSorted', name: 'Is Sorted', inputs: [{l:'Array', k:'arr'}] },
        { id: 'binarySearchFirstOccurrence', name: 'Binary Search (First)', inputs: [{l:'Sorted Array', k:'arr'}, {l:'Target', k:'val'}] }
    ],
    string: [
        { id: 'reverse', name: 'Reverse String', inputs: [{l:'String', k:'s1'}] },
        { id: 'isPalindrome', name: 'Is Palindrome', inputs: [{l:'String', k:'s1'}] },
        { id: 'longestSubstringWithoutRepeatingCharacters', name: 'Longest Unique Substr', inputs: [{l:'String', k:'s1'}] },
        { id: 'isAnagram', name: 'Is Anagram', inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}] },
        { id: 'countOccurrences', name: 'Count Occurrences', inputs: [{l:'Source', k:'s1'}, {l:'Target', k:'s2'}] },
        { id: 'toTitleCase', name: 'Title Case', inputs: [{l:'String', k:'s1'}] },
        { id: 'countUniqueWords', name: 'Count Unique Words', inputs: [{l:'Sentence', k:'s1'}] },
        { id: 'cleanWhitespace', name: 'Clean Whitespace', inputs: [{l:'String', k:'s1'}] },
        { id: 'splitByLength', name: 'Split By Length', inputs: [{l:'String', k:'s1'}, {l:'Delimiter', k:'s2'}, {l:'Min Length', k:'val'}] },
        { id: 'removeDuplicateChars', name: 'Remove Dup Chars', inputs: [{l:'String', k:'s1'}] },
        { id: 'isAlphabetic', name: 'Is Alphabetic', inputs: [{l:'String', k:'s1'}] }
    ],
    graph: [
        { id: 'shortestPathBFS', name: 'Shortest Path (BFS)', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}] },
        { id: 'traverseDFS', name: 'Traverse DFS', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}] },
        { id: 'containsCycleUndirected', name: 'Has Cycle', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] },
        { id: 'maxDegree', name: 'Max Degree', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] },
        { id: 'topologicalSortKahn', name: 'Topological Sort', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] },
        { id: 'isTree', name: 'Is Tree', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] },
        { id: 'countConnectedComponents', name: 'Connected Components', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] },
        { id: 'shortestPathDijkstra', name: 'Dijkstra', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v w per line)', k:'s1', type:'area'}, {l:'Start Node', k:'val2'}] },
        { id: 'isSinkNode', name: 'Is Sink Node', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}, {l:'Check Node', k:'val2'}] },
        { id: 'computeIndegrees', name: 'Compute Indegrees', inputs: [{l:'Num Nodes', k:'val'}, {l:'Edges (u v per line)', k:'s1', type:'area'}] }
    ],
    dp: [
        { id: 'countSubsetsWithSumK', name: 'Count Subsets (Sum K)', inputs: [{l:'Array', k:'arr'}, {l:'K', k:'val'}] },
        { id: 'countPartitionsWithGivenDifference', name: 'Count Partitions (Diff)', inputs: [{l:'Array', k:'arr'}, {l:'Diff', k:'val'}] },
        { id: 'maxSumNonAdjacent', name: 'Max Sum Non-Adj', inputs: [{l:'Array', k:'arr'}] },
        { id: 'longestCommonSubsequence', name: 'LCS', inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}] },
        { id: 'longestPalindromicSubsequence', name: 'LPS', inputs: [{l:'String', k:'s1'}] },
        { id: 'longestCommonSubstring', name: 'Longest Common Substr', inputs: [{l:'String 1', k:'s1'}, {l:'String 2', k:'s2'}] },
        { id: 'minInsertionsToMakePalindrome', name: 'Min Insertions (Palindrome)', inputs: [{l:'String', k:'s1'}] },
        { id: 'longestIncreasingSubsequence', name: 'LIS', inputs: [{l:'Array', k:'arr'}] },
        { id: 'minPathSum', name: 'Min Path Sum (Grid)', inputs: [{l:'Rows', k:'val'}, {l:'Cols', k:'val2'}, {l:'Grid (row per line, comma sep)', k:'s1', type:'area'}] }
    ],
    stack: [
        { id: 'reverseStack', name: 'Reverse Stack', inputs: [{l:'Stack (space sep)', k:'arr'}] },
        { id: 'isBalanced', name: 'Is Balanced', inputs: [{l:'Expression', k:'s1'}] },
        { id: 'sortStack', name: 'Sort Stack', inputs: [{l:'Stack (space sep)', k:'arr'}] },
        { id: 'evaluatePostfix', name: 'Evaluate Postfix', inputs: [{l:'Expression', k:'s1'}] },
        { id: 'nextGreaterElement', name: 'Next Greater Element', inputs: [{l:'Array', k:'arr'}] },
        { id: 'longestValidParentheses', name: 'Longest Valid Paren', inputs: [{l:'String', k:'s1'}] },
        { id: 'infixToPostfix', name: 'Infix to Postfix', inputs: [{l:'Infix', k:'s1'}] },
        { id: 'findMiddleElement', name: 'Find Middle', inputs: [{l:'Stack (space sep)', k:'arr'}] },
        { id: 'isPermutation', name: 'Is Permutation', inputs: [{l:'Stack 1', k:'arr'}, {l:'Stack 2', k:'arr2'}] },
        { id: 'removeAllOccurrences', name: 'Remove All Occur', inputs: [{l:'Stack', k:'arr'}, {l:'Item to Remove', k:'s1'}] }
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
    document.getElementById('func-desc').textContent = "Input parameters for " + func.name;
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