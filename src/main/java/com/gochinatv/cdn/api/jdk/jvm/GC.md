
1. http://www.importnew.com/1993.html
2. http://www.importnew.com/2057.html
3. http://www.importnew.com/3146.html
4. http://www.importnew.com/3151.html
5. http://www.importnew.com/13954.html
6. https://www.cnblogs.com/paddix/p/5309550.html
7. https://blog.csdn.net/qq_34707744/article/details/79288787

> 在学习GC之前，你首先应该记住一个单词：“stop-the-world”。Stop-the-world会在任何一种GC算法中发生。Stop-the-world意味着 JVM 因为要执行GC而停止了应用程序的执行。当Stop-the-world发生时，除了GC所需的线程以外，所有线程都处于等待状态，直到GC任务完成。GC优化很多时候就是指减少Stop-the-world发生的时间。