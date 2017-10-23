#coding:utf-8

__author__ = "Ionésio Junior"


class CircularQueue():
	''' Implementation of circular queue data structure
           
            Attributes:
		queueArray[] : list of elements stored in structure
		tail(int) : index of queue tail
		head(int) : index of queue head
		elements(int) : number of elements in queue
		size(int) : total capacity of queue
        '''
	__queueArray  = None
	__tail = None;
	__head = None;
	__elements = None;
	__size = None;

	def __init__(self,size = 10):
		''' Queue Constructor initialize attributes with default value
			
		    Args:
			size(int) : Size of queue list,Defaults to 10.
		'''
		self.__queueArray = []
		self.__tail = -1
		self.__head = -1
		self.__elements = 0
		self.__size = size
	
	
	def enqueue(self,element):
		''' Insert new element in tail of queue or raise an exception if queue is full.
		    (None elements aren't allowed).
		    Complexity: O(1)
	
		    Args:
			element(optional) : element to be inserted
		
		    Raises:
			Exception : When queue is Full
		'''
		if(element == None):
			return;
		if(self.isFull()):
			raise Exception("Queue is Full!!")
		else:
			if(element != None):
				self.__elements = self.__elements + 1
				if(self.__head == -1 and self.__tail == -1):
					self.__head = (self.__head + 1) % self.__size
				
				self.__tail = (self.__tail + 1) % self.__size
				self.__queueArray.insert(self.__tail,element)	
			
				
	def dequeue(self):
		''' Remove  and return element in the head of the queue or raise an exception if queue is empty
		    Complexity : O(1)
		
		    Returns:
			element (optional) : element removed.
		
		    Raises:
			Exception: When queue is Empty
		'''
		if(self.isEmpty()):
			raise Exception("Queue is Empty!!")
		else:
			self.__elements = self.__elements - 1
			removedElement = self.__queueArray[self.__head]
			self.__head = (self.__head + 1) % self.__size
			return removedElement
		

	def head(self):
		''' Return element in the head of the queue without removed it.
		    (Return None if queue is empty).
		    Complexity: O(1)		

		    Returns:
			element(optional) : head elment / None
		'''
		if(self.isEmpty()):
			return None
		else:
			return self.__queueArray[self.__head]
	
	
	def isEmpty(self):
		'''  Return true if queue is empty or false,otherwise.
		     Complexity: O(1)	     
	
		     Returns:
			boolean
		'''
		return (self.__elements == 0)
	
	
	def isFull(self):
		''' Return true if queue is full or false,otherwise.
		    Complexity: O(1)			

		    Returns:
			boolean
		'''
		return (self.__elements == self.__size)

