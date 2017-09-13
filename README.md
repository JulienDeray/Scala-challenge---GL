# Scala challenge (GL)

## Task 1

The proposed implementation takes full advantage of Scala's functional programming techniques, though the algorithm could be optimised:
* the method is not tail-recursive, which could lead to memory problems if it runs with very long lists
* the time complexity is O(n^2), which is definitely not optimum.

We could improve the algorithm by caching known results of `lcs`. 