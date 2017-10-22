#coding: utf-8

__author__ = "Ionesio Junior"

from DoubleNode import DoubleNode


class DoubleLinkedList():
	''' Double Linked List in iterative implementation
	
           Attributes:
		head(DoubleNode) :  head of double linked list
		tail(DoubleNode) : tail of double linked list
	'''
	
	__head = None;
	__tail = None;


	def __init__(self):
		''' Empty Constructor of Double Linked List, initialize/connect head/tail Nodes'''
		self.__head = DoubleNode()
		self.__tail = DoubleNode()
		self.__head.setNext(self.__tail)
		self.__tail.setPrevious(self.__head)
	
	
	def isEmpty(self):
		''' Return true if linked list is empty or false,otherwise.
		    Complexity: O(1)
	
		    Returns:
			boolean
		'''
		return (self.__head.isEmpty())
	
	
	def size(self):
		''' Return the size of double linked list.
		    Complexity : O(n)
			
		    Returns:
			size(int) : How many element have in this linked list.
			
		'''
		size = 0
		aux = self.__head
		while(not(aux.isEmpty())):
			aux = aux.getNext()
			size = size + 1
		return size
	
	
	def search(self, element):
		''' Search an specific element into this double linked list and return it if found or return None,otherwise.
		    Complexity : O(n)
			
		    Args:
			element(optional) : element to be searched
		
		    Returns:
			element(optional) : found element  / None
		
		'''
		if(element != None):
			aux = self.__head
			while(not(aux.isEmpty())):
				if(aux.getData() == element):
					return aux.getData()
				aux = aux.getNext()
	
	def insert(self,element):
		''' Insert an element in tail of double linked list.(None element aren't allowed).
		    Complexity: O(1)

		    Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			if(self.isEmpty()):
				self.insertFirst(element)
			else:
				node = DoubleNode(element,self.__tail,DoubleNode())
				self.__tail.setNext(node)
				self.__tail = node
	

	def remove(self,element):
		''' Remove an specific element from the list
		    Complexity: O(n)
		
		    Args:
			element(optional) : element to be removed
		'''
		if(element != None and not(self.isEmpty())):
			if(self.__head.getData() == element):
				self.removeFirst()
			else:
				aux = self.__head
				while(not(aux.isEmpty())):
					if(aux.getData() == element):
						aux.getPrevious().setNext(aux.getNext())
						aux.getNext().setPrevious(aux.getPrevious())
						if(aux.getNext().isEmpty()):
							self.__tail = aux.getPrevious()
						break
					aux = aux.getNext()
	
	
	def toArray(self):
		''' Return an list with all of list elements
		    Complexity: O(n)
			
		   Returns:
			list[elements] : all of double linked list elements.
		'''
		result = []
		aux = self.__head
		while(not(aux.isEmpty())):
			result.append(aux.getData())
			aux = aux.getNext()
		return result
	
	
	def insertFirst(self,element):
		''' Insert an element in first position of double linked list (None elements aren't allowed).
		    Complexity: O(1)	 

	
		    Args;
			elment(optional) : element to be inserted
		'''
		if(element != None):
			if(self.isEmpty()):
				newHead = DoubleNode(element, DoubleNode(),DoubleNode())
				self.__head = newHead
				self.__tail = newHead
			else:
				newHead = DoubleNode(element,DoubleNode(),self.__head)
				self.__head.setPrevious(newHead)
				self.__head = newHead
	
	
	def removeFirst(self):
		''' Remove the first element of double linked list.
		    Complexity: O(1)
		'''
		if(self.isEmpty()):
			return
		else:
			self.__head.getNext().setPrevious(DoubleNode())
			if(self.size() == 1):
				self.__tail = self.__head.getNext()
			self.__head = self.__head.getNext()
	
	
	def removeLast(self):
		''' Remove the last element of double linked list. 
		    Complexity: O(1)
		
		'''
		if(not(self.isEmpty())):
			self.__tail.getPrevious().setNext(DoubleNode())
			if(self.size() == 1):
				self.__head = self.__tail.getPrevious()
			self.__tail = self.__tail.getPrevious()
	
		
