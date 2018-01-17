一个简单的爬虫例子：

采用HttpClient进行网页的抓取
采用Jsoup进行

采用宽度优先的遍历算法（转）：
	(1) 顶点 V 入队列。
	(2) 当队列非空时继续执行，否则算法为空。
	(3) 出队列，获得队头节点 V，访问顶点 V 并标记 V 已经被访问。
	(4) 查找顶点 V 的第一个邻接顶点 col。
	(5) 若 V 的邻接顶点 col 未被访问过，则 col 进队列。
	(6) 继续查找 V 的其他邻接顶点 col，转到步骤(5)，若 V 的所有邻接顶点都已经被访问过，则转到步骤(2)

宽度优先时，未被访问的url需要采用先进先出（FIFO）的访问方式，所以需要采用队列的数据结构，本例采用了LinkedList双向列表保存未访问的URL
已访问的列表可以采用HashSet的数据结构。同时在入未访问队列时需要保证URL的唯一性，否则有可能会出现死循环