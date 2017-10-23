#coding: utf-8
__author__ = "Ionesio Junior"

class Node(object):
	'''
        Node class implementation used Binary Search Tree/AVL/Splay
	
	Attributes:
		data(optional) : value stored in this node
		left(Node) : left son of this node
		right(Node) : right son of this node
		parent(Node) : parent of this node
	'''	
	def __init__(self,data=None,left=None,right=None,parent=None):
		self.__data=data
		self.__left = left
		self.__right = right
		self.__parent = parent
	
	
	def isEmpty(self):
		return (self.__data == None)
	
	
	def isLeaf(self):
		return (self.__left.isEmpty() and self.__right.isEmpty())
	
	
	def getData(self):
		return self.__data
	def getLeft(self):
		return self.__left
	def getRight(self):
		return self.__right
	def getParent(self):
		return self.__parent


	def setData(self,newData):
		self.__data = newData
	def setLeft(self,newLeft):
		self.__left = newLeft
	def setRight(self,newRight):
		self.__right = newRight
	def setParent(self,newParent):
		self.__parent = newParent

		
	def __eq__(self,other):
		''' Overload of == operation '''
		return self.__data == other	
