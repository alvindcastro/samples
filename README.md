Some explanation from the changes:

The other implementation is to use a merge sort (https://en.wikipedia.org/wiki/Merge_sort) which is what the Collections.sort is using (http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html).

I've also used a BufferedReader on the merge sort as said method is Synchronous whereas Scanner is not. But I did not implemented a thread-based file reader as I'm not sure if that would address the bottleneck when processing data (or if there is even a bottleneck).
