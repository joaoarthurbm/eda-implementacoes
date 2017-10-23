#coding: utf-8

__author__  = "Ionesio Junior"


class RecursiveLinkedList(object):
	''' Single Linked List in recursive implementation
	   
	    Attributes:
		data(optional) : data stored in this object
		nextNode(RecursiveLinkedList) : next Recursive Node
	'''
	
	__data = None;
	__nextNode = None;
	def __init__(self,data=None,nextNode=None):
		''' Constructor of Recursive Linked List class, initialize attributes.
		
		    Args:
			data(optional) : data to be stored in this object
			nextNode(RecursiveLinkedList) : next recursive object after this
		'''
		self.__data = data
		self._nextNode = nextNode
	
	
	def isEmpty(self):
		''' Return true if linked list is empty or false,otherwise.
	            Complexity: O(1)	
	
		    Returns:
			boolean
		'''
		return (self.__data == None)
	
	
	def size(self):
		''' Return size of linked list.
		    Complexity: O(n)
	
		    Returns:
			size(int) : how many elements have in linked list
		'''
		if(self.isEmpty()):
			return 0;
		else:
			return 1 + self.__nextNode.size()
	
	
	def search(self,element):
		''' Search an specific element in linked list and return if found it.(Return None if can't found element)
		    Complexity: O(n) 	   

		    Args:
			element (optional) : element to be searched
		
		    Returns:
			foundElement (optional) : found element / None.
		'''
		if(self.isEmpty() or element == None):
			return None
		else:
			if(self.__data == element):
				return self.__data
			else:
				return self.__nextNode.search(element)
	
	
	def insert(self,element):
		''' Insert new element in last position of linked list (None elements aren't allowed).
		    Complexity: O(n)

		    Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			if(self.isEmpty()):
				self.__data = element
				self.__nextNode = RecursiveLinkedList()
			else:
				self.__nextNode.insert(element)
	
	
	def remove(self,element):
		'''  Remove an specific element in linked list.
		     Complexity: O(n)
		
		     Args:
			element (optional) : element to be removed
		'''
		if(not(self.isEmpty())):
			if(self.__data == element):
				self.__data = self.__nextNode.getData()
				self.__nextNode = self.__nextNode.getNext()
			else:
				self.__nextNode.remove(element)
	
	
	def toArray(self):
		''' Return an list of all linked list elements
		    Complexity: O(n)
	
		    Returns:
			List[elements] ; all linked list elements.
		'''
		result = []
		if(self.size() > 0):
			self.__getList(result)
		return result

	
	def __getList(self,array):
		''' Recursive Method to fill list
		
		    Args:
			array[elements] : array with linked list elements.
		'''
		if(self.__data != None):
			array.append(self.__data)
			self.__nextNode.__getList(array)

	
	def getNext(self):
		''' Return next node
		
		    Args:
			nextNode(RecursiveLinkedList) :  next linked list node.
		'''
		return self.__nextNode


	def getData(self):
		''' Return data value of this node
		
		    Returns:
			data(optional) : value of data
			
		'''
		return self.__data
