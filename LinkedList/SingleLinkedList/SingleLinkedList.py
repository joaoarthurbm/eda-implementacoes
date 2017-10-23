#coding : utf-8
from Node import Node

__author__ = "Ionesio Junior"


class SimpleLinkedList():
	''' Single linked list in iterative implementation
		
	    Attributes:
		head(Node) : head of single linked list
	'''
	
	__head = None;

	def __init__(self):
		''' Empty single linked list constructor '''
		self.__head = Node()
	
	
	def isEmpty(self):
		''' Return true if linked list is empty or false,otherwise.
		    Complexity: O(1)		

		    Returns:
			boolean
		'''
		return self.__head.isEmpty()
	

	def size(self):
		''' Return size of linked list
		    Complexity: O(n)
	
		    Returns:
			size(int) : lenght of linked list
		'''
		size = 0
		aux = self.__head
		while(not(aux.isEmpty())):
			aux = aux.getNext()
			size = size + 1
		return size


	def search(self,element):
		''' Search an specific element in linked list, if found return it, else return None
		    (None element aren't allowed).
		    Complexity : O(n)

		    Args:
			element(optional) : element to be searched
		
		    Returns:
			foundData(optional) : foundElement / None
	
		'''
		aux = self.__head
		while(not(aux.isEmpty())):
			if(aux.getData() == element):
				return aux.getData()
			aux = aux.getNext()
		return None

	
	def insert(self,element):
		''' Insert an element in last position of linked list.
		    (None element aren't allowed).
		    Complexity: O(n)
		
		    Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			aux = self.__head
			while(not(aux.isEmpty())):
				aux = aux.getNext()
			aux.setData(element)
			aux.setNext(Node())
	
	def remove(self,element):
		''' Remove an specific element in linked list.
	            Complexity: O(n)
		
		    Args:
			element(optional) : element to remove
		'''
		if(element != None):
			if(element == self.__head.getData()):
				self.__head = self.__head.getNext()
			else:
				aux = self.__head
				while(not(aux.isEmpty())):
					if(aux.getData() == element):
						aux.setData(aux.getNext().getData())
						aux.setNext(aux.getNext().getNext())
						break;
					aux = aux.getNext()

	
	def toArray(self):
		''' Return an list with all elements of linked list
		    Complexity: O(n)
	
		    Returns:
			List[elements] : list of all elements. 
		'''
		array = []
		aux = self.__head
		while(not(aux.isEmpty())):
			array.append(aux.getData())
			aux = aux.getNext()
		return array
	
