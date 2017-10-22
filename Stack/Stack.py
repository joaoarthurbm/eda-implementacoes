#coding:utf-8

__author__  = "Ionésio Junior"


class Stack():
	""" Stack Structure Implementation 
		
	    Attributes:
		stackList[] = list of elements in stack
		size(int) = size of stack
		top(int) = index of top
	"""
	__stackList = None;
	__size = None;
	__top = None;
	def __init__(self,size = 10):
		'''' Stack Constructor.
			
		     Initialize attributes with default value.
		'''
		self.__stackList = []
		self.__size = size
		self.__top = -1
		

	def push(self,element):
		''' Insert new element in the top of the stack or raise an exception if stack is full
		    Complexity: O(1)		

		    Args:
		 	element(optional) : element to be inserted
		
		    Raises:
			Exception : When stack is full
		'''
		if(element != None and  not(self.isFull())):
			self.__top = self.__top + 1
			self.__stackList.insert(self.__top,element)
		elif element == None:
			return;
		else:
			raise Exception("Stack is Full!!")


	def pop(self):
		''' Remove element in top of the stack or raise an exception if stack is empty
		    Complexity: O(1)

		    Returns:
			 removedElement(optional) : element removed
		    Raises:
			Exception: When stack is empty
		'''
		if(not(self.isEmpty())):
			removedElement = self.__stackList[self.__top]
			del self.__stackList[self.__top]
			self.__top = self.__top - 1
			return removedElement
		else:
			raise Exception("Stack is empty!!")


	def top(self):
		''' Return element in top of the stack without remove it, or return None if stack is empty
		    Complexity : O(1)		    

		   Returns:
			element(optional) : element in top of the stack / None
		'''
		if(not(self.isEmpty())):
			return self.__stackList[self.__top]
		else:
			return None
	def isEmpty(self):
		''' Return true if stack is empty or false,otherwise.
		    Complexity: O(1)	
	
		    Returns:
			boolean 
		'''
		return (self.__top < 0)
	
	
	def isFull(self):
		''' Return true if stack is full or false,otherwise
		    Complexity: O(1)		   

                    Returns:
			boolean
		'''
		return (self.__size - 1 ==  self.__top)
