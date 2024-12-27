class Solution:
    def minInterval(self, intervals: List[List[int]], queries: List[int]) -> List[int]:
        intervals.sort()
        minheap=[]
        res={}
        i = 0
        for q in sorted(queries):
            while i < len(intervals) and intervals[i][0] <= q:
                l, r = intervals[i]
                # heapq module in python  always makes sure smallest element is  at 0th index of given list as the first argument
                heapq.heappush(minheap, (r - l + 1, r))
                i += 1

            while minheap and minheap[0][1] < q:
                heapq.heappop(minheap)
            res[q] = minheap[0][0] if minheap else -1
        # list comprehension 
        return [res[q] for q in queries]


        
