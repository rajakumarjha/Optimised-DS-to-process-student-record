# Optimised-DS-to-process-student-record

This is a java application to process student data considering the time complexity.

User should be able to (Functionality)
1. Add new student
2. Search student with Id
3. delete student
4. Exit this app saving all the data to a file and read the data when program runs again.

## Proposed Solution:  I have used Nested Hashtable. 
         Time: Insert: O(1), delete:O(1) and search: O(1)
         Memory: O(1) no extra memory other than the storage
         
## Other methods: 
### Chaining Hashtable: 
          Time: Insert: O(n) in worst case when one chain is created
          Search: O(n) in worst case when one chain is created
          Delete: O(n) in worst case when one chain is created
          Memory: O(1) no extra memory other than the storage
### HashTable where each element is a tree:
          Time: Insert: O(logn) in worst case when search the correct place
          Search: O(logn) search in balanced tree
          Delete: O(logn) search and delete in balanced tree
          Memory: O(1) no extra memory other than the storage
### Tree of Linked List
          Time: Insert: O(nlogn) in worst case when search the Linked list and after that tree
          Search: O(nlogn) in worst case when search the Linked list and after that tree
          Delete: O(nlogn) in worst case when search the Linked list and after that tree
          Memory: O(n) store extra linked list address
