#coding: utf-8

__author__  = "Ionésio Junior"

class SimpleQueue():
	''' Implementation of simple queue data structure 
		
	    Attributes:
		queueList[] : list of elements in queue
		size(int) : size of list
		tail(int) : index of queue tail	
	'''
	def __init__(self,size = 10):
		''' Constructor of Simple Queue initialie attributes using default values.
		
		    Args:
			size(int) : capacity of queue, Default to 10.
		'''
		self.__queueList = []
		self.__size = size
		self.__tail = -1

	
	def enqueue(self,element):
		''' Insert new element in tail of the queue or raise and exception if queue is full 
		    (None elements aren't allowed).
		    Complexity: O(1)			

		    Args:
			element(optional) : element to be inserted
		
		    Raises:
			Exception: When queue is full
		'''
		if(element == None):
			return;
		if(not(self.isFull())):
			self.__tail = self.__tail + 1
			self.__queueList.insert(self.__tail,element)
		else:
			raise Exception("Queue is Full!!")

	
	def dequeue(self):
		''' Remove and return element in head of the queue or raise and exception if queue is empty
		    Complexity: O(n)
	
		    Returns:
			element(optional) : removed element
		
		    Raises:
			Exception : when queue is empty
		
		'''
		if(not(self.isEmpty())):
			removedElement = self.__queueList[0]
			self.__shiftLeft()
			self.__tail = self.__tail - 1
			return removedElement
		else:
			raise Exception("Queue is Empty!!")
	

	def __shiftLeft(self):
		''' Move each of queue elements to previous position , after remove some node '''
		for i in range(self.__tail):
			self.__queueList[i] = self.__queueList[i+1]
	

	def head(self):
		''' Return the element of queue head without remove it.
		    (If queue is Empty, return None).
		    Complexity: O(1)		
	
		    Returns:
			element (optional): element in head position/None.
		'''
		if(not(self.isEmpty())):
			return self.__queueList[0]
		else:
			return None
	

	def isEmpty(self):
		''' Return true if queue is empty or false,otherwise.
		    Complexity: O(1)	
	
		    Returns:
			boolean
		'''
		return (self.__tail < 0)
	
	def isFull(self):
		'''Return true if queue is full or false,otherwise.
		   Complexity: O(1)
	
		   Returns:
			boolean
		'''
		return (self.__tail == self.__size - 1)
