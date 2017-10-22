#coding: utf-8

__author__ = "Ionesio Junior"


class RecursiveDoubleLinkedList(object):
	'''
		Double Linked List in recursive implementation	
		
		Attributes:
			data(optional) : data of this node.
			nextNode(RecursiveDoubleLinkedList) : next recursive node
			previous(RecursiveDoubleLinkedList) : previous recursive node
	'''	
	__data = None;
	__nextNode = None;
	__previous = None;

	def __init__(self,data = None,nextNode = None,previous = None):
		''' Constructor of Recursive Double Linked List. Initialize attributes 
		
		    Args:
			data(optional) : data value of this node
			nextNode(RecursiveDoubleLinkedList) :  next recursive node
			previous(RecursiveDoubleLinkedList) : previous recursive node
		'''
		self.__data = data
		self.__nextNode = nextNode
		self.__previous = previous
	
	
	def isEmpty(self):
		''' Return true if double linked list is empty or false,otherwise.
		    Complexity: O(1)	
	
		    Returns:
			boolean
		'''
		return (self.__data == None)
	
	
	def size(self):
		''' Return size of double linked list.
		    Complexity: O(n)
		
		    Returns:
			size(int) : how many elements have in double linked list
		'''
		if(self.isEmpty()):
			return 0;
		else:
			return 1 + self.__nextNode.size()
	
	
	def search(self,element):
		''' Search an specific element in list and return it if find or return None.
		    Complexity: O(n)
		
		    Args:
			element(optional) : element to be searched
		
		    Returns:
			element(optional) : found element  / None
		'''
		if(self.isEmpty() or element == None):
			return None
		else:
			if(self.__data == element):
				return self.__data
			else:
				return self.__nextNode.search(element)
	
	
	def insert(self,element):
		''' Insert an element in last position of the double linked list.
		    (None element aren't allowed).
		    Complexity: O(n)
		
		    Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			if(self.isEmpty()):
				self.__data = element
				self.__nextNode = RecursiveDoubleLinkedList()
				self.__nextNode.setPrevious(self)
				if(self.__previous == None):
					self.__previous = RecursiveDoubleLinkedList(None,self,None)
			else:
				self.__nextNode.insert(element)
	
	
	def remove(self,element):
		''' Remove an specific element in list.
		    Complexity: O(n)
			
		   Args:
			element(optional) : element to be removed
		'''
		if(element != None):
			if(not(self.isEmpty())):
				if(self.__data == element):
					self.removeFirst()
				else:
					self.__nextNode.remove(element)
	
	
	def toArray(self):
		''' Return an list with all of double linked list elements.
		    Complexity = O(n)	

		    Returns:
			list[element] : all elements of list
		'''
		result = []	
		if(self.size() > 0):
			result.append(self.__data)
			self.__nextNode.getList(result)
		return result
	
	
	def getList(self,array):
		if(self.__data != None):
			array.append(self.__data)
			self.__nextNode.getList(array)
	
	
	def insertFirst(self,element):
		''' Insert an element in first position of the list.
		    (None element aren't allowed).
		    Complexity = O(1);
		
		    Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			if(self.isEmpty()):
				nextNode = RecursiveDoubleLinkedList()
				self.__data = element
				self.__nextNode = nextNode
				self.__previous = nextNode
			else:
				nextNode = RecursiveDoubleLinkedList(self.__data,self.__nextNode,self)
				self.__nextNode.setPrevious(nextNode) 
				self.__data = element
				self.__nextNode = nextNode
	
	def removeFirst(self):
		''' Remove the first element in list
		    Complexity: O(1)
		
		'''
		if(not(self.isEmpty())):
			if(self.__nextNode.isEmpty()):
				self.__data = None
				self.__nextNode = None
				self.__previous = None
			else:
				self.__data = self.__nextNode.getData()
				self.__nextNode.getNext().setPrevious(self)
				self.__nextNode = self.__nextNode.getNext()
	
	def removeLast(self):
		''' Remove the last element in list
		    Complexity: O(n)
		'''
		if(not(self.isEmpty())):
			if(self.__nextNode.isEmpty()):
				self.__data = None
				self.__next = None
				self.__previous = None
			else:
				self.__nextNode.removeLast();

	####################################	AUXILIAR METHODS	###############################################
	
	def getPrevious(self):
		return self.__previous
	def getNext(self):
		return self.__nextNode
	def getData(self):
		return self.__data
	
	
	def setPrevious(self,newPrevious):
		self.__previous = newPrevious
	def setNext(self,newNext):
		self.__nextNode = newNext
	def setData(self,newData):
		self.__data = data

