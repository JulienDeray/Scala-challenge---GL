# Scala challenge (GL)

Each task comes with an object and a suite of tests. All tests can be ran with `sbt test`.

## Task 1

**Exercise**: Longest common (discontiguous) subsequence of two Strings.

The proposed implementation takes full advantage of Scala's functional programming techniques, though the algorithm could be optimised:
* the method is not tail-recursive, which could lead to memory problems if it runs with very long lists
* the time complexity is O(n^2), which is definitely not optimum.

We could improve the algorithm by caching known results of `lcs`. (edit: this optimisation has been implemented (22d753a))

## Task 2

**Exercise**: Given integers (eg. [2, 3, 5, 6]) and a target number (eg. 42), the program could return an expression (eg. `(2 + 5) * 6`).

My first implementation was only able to resolve affine expressions (`(x + y) * z)`, y and z being muted if respectively equal to 0 and 1).
I then improved it by adding the possible permutations of operations to build one binary trees per permutation of numbers, removing the constraint of having one addition and one multiplication.

Though, both solutions are based on bruteforce, computing all the permutations for numbers and operations, and could be improved. Ideas:
* cache the result of known operations
* use basic algebra to find the end of an expression quickly (eg. for `2 + y = 5`, check if `3` is in the list)