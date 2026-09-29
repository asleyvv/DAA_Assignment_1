## A. Project Overview
This project implements and analyzes four classic divide-and-conquer algorithms to understand their theoretical and practical efficiency limits[cite: 1]. The implementations include MergeSort (with an insertion sort cutoff), QuickSort (with randomized pivoting and recursion tail-call optimization), Deterministic Select (Median of Medians), and the Closest Pair of Points[cite: 1]. 

## B. Algorithm Analysis
1. **MergeSort**: Divides the array into halves until the cutoff limit is reached, then merges the sorted halves.
   * **Complexity**: $\Theta(n \log n)$ time, $O(n)$ space.
   * **Recurrence**: $T(n) = 2T(n/2) + O(n)$. By Master Theorem, case 2, it evaluates to $\Theta(n \log n)$[cite: 1].
2. **QuickSort**: Partitions around a random pivot. Space is optimized by recursing only on the smaller partition[cite: 1].
   * **Complexity**: Average $\Theta(n \log n)$, worst-case $O(n^2)$ time.
   * **Recurrence**: $T(n) = T(q) + T(n-q-1) + O(n)$.
3. **Deterministic Select**: Groups elements by 5, finds their medians, and recursively finds the median of those medians to serve as a guaranteed good pivot[cite: 1].
   * **Complexity**: Worst-case $O(n)$ time[cite: 1].
   * **Recurrence**: $T(n) \le T(n/5) + T(7n/10) + O(n)$. By Akra-Bazzi, this resolves to $O(n)$[cite: 1].
4. **Closest Pair of Points**: Sorts by X, divides into halves, and checks a localized strip around the mid-line ordered by Y to ensure bound checks[cite: 1].
   * **Complexity**: $\Theta(n \log n)$ time[cite: 1].

## D. Discussion
* **Do the results match theoretical complexity?** Yes, the CSV execution times demonstrate MergeSort remaining highly stable regardless of input type, whereas unoptimized QuickSort degrades on duplicate inputs.
* **Why does smaller-first recursion help QuickSort?** It guarantees that the recursion stack depth never exceeds $O(\log n)$[cite: 1], preventing `StackOverflowError` on heavily skewed data distributions.
* **Why does Median-of-Medians guarantee O(n)?** By guaranteeing the pivot is greater than at least 30% of the elements and less than at least 30%, the recurrence tree is strictly bounded from devolving into an $O(n^2)$ linear tree[cite: 1].
* **Why is divide-and-conquer Closest Pair faster?** Instead of checking $n(n-1)/2$ combinations ($O(n^2)$), the strip boundary condition limits point comparisons across the divide line to a maximum of 7 points[cite: 1].
---

**P.S.** While this README documentation and analytical write-up were structured and drafted with the assistance of AI, all Java source code, algorithmic implementations, and experiment benchmarks were written entirely by me.
