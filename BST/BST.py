#coding: utf-8
from Node import Node

__author__ = "Ionesio Junior"


class BinarySearchTree(object):
	''' Binary Search Tree Class implementation
		
	   Attributes:
		root(Node) : root of the tree	
	'''	
	def __init__(self):
		''' BST constructor , initialize attributes '''
		self.__root = Node();
	
	
	def isEmpty(self):
		''' Return true if bst is empty or false,otherwise 
			
		    Returns:
			boolean
		'''
		return self.__root.isEmpty()

	
	
	def insert(self,element):
		''' Insert some element in correct leaf (left if element is lesser than the current node ,or right otherwise)
		    (None / repeated element not allowed)

		    Args:
			element(optional) :element to be inserted
		'''
		if(element != None):
			self.recursiveInsert(element,self.__root,self.__root.getParent())
		
	def recursiveInsert(self,data,node,parent):
		if(not(node.isEmpty())):
			if(node.getData() == data):
				return
			if(node.getData() > data):
				self.recursiveInsert(data,node.getLeft(),node)
			else:
				self.recursiveInsert(data,node.getRight(),node)
		else:
			node.setData(data)
			node.setRight(Node())
			node.setLeft(Node())
			node.setParent(parent)
	
	def remove(self,element):
		''' Search an element and remove it.
		    if found node is leaf -> only remove it
		    if found node only one son -> replace your position using your son
		    if found node have two sons -> replace your position using larger son
		
		    Args:
			element(optional) : element to be removed
		    
		'''
		foundNode = self.search(element)
		if(not(foundNode.isEmpty())):
			self.recursiveRemove(foundNode)
	
	def recursiveRemove(self,node):
		if(node.isLeaf()):
			node.setData(None)
			node.setLeft(None)
			node.setRight(None)
		elif(node.getLeft().isEmpty()):
			node.setData(node.getRight().getData())
			node.getRight().getRight().setParent(node)
			node.getRight().getLeft().setParent(node)
			node.setRight(node.getRight().getRight())
		elif(node.getRight().isEmpty()):
			node.setData(node.getLeft().getData())
			node.getLeft().getRight().setParent(node)
			node.getLeft().getLeft().setParent(node)
			node.setLeft(node.getLeft().getLeft())
		else:
			removedValue = node.getData()
			sucessor = self.sucessor(removedValue)
			node.setData(sucessor.getData())
			sucessor.setData(removedValue)
			self.recursiveRemove(sucessor)
	
	def search(self,element):
		''' Search an Node and return it,if not found return Empty Node
		
		    Args:
			element(optional) : element to be searched
			
		   Returns:
			Node(Node) : node with element value or empty node if value not found in tree
		'''
		if(element == None or self.__root.isEmpty()):
			return Node()
		else:
			return self.__recursiveSearch(element,self.__root)
	
	def __recursiveSearch(self,element,node):
		if(not(node.isEmpty())):
			if(node.getData() == element):
				return node
			elif(node.getData() > element):
				return self.__recursiveSearch(element,node.getLeft())
			else:
				return self.__recursiveSearch(element,node.getRight())
		else:
			return node

	def height(self):
		''' Return max height of this tree
		
		    Args:
			height(int) : max height of this tree
		'''
		return self.recursiveHeight(self.__root) - 1
	
	def recursiveHeight(self,node):
		if(not(node.isEmpty())):
			leftHeight = self.recursiveHeight(node.getLeft())
			rightHeight = self.recursiveHeight(node.getRight())
			if(leftHeight > rightHeight):
				return leftHeight + 1
			else:
				return rightHeight + 1
		else:
			return 0
	
	def size(self):
		''' Return how many elements have in this tree
			
		    Args:
			size(int) : number of elements inside the tree
		'''
		return self.__recursiveSize(self.__root)
	
	def __recursiveSize(self,node):
		if(not(node.isEmpty())):
			return 1 + self.__recursiveSize(node.getLeft()) + self.__recursiveSize(node.getRight())
		else:
			return 0
	
	
	def maximum(self):
		''' Return node with max element stored in current bst tree,or None if tree is empty
		
		   Args:
			Node(Node/None) : node with max element stored/ None
		'''
		if(self.size() == 0):
			return None
		else:
			return self.recursiveMaximum(self.__root)
	
	def recursiveMaximum(self,node):
		if(not(node.getRight().isEmpty())):
			return self.recursiveMaximum(node.getRight())
		else:
			return node
	
	def minimum(self):
		''' Return node with min element stored in current bst tree,or None if tree is empty
		
		    Args:
			Node(Node/None) : node with min element stored / None
		'''
		if(self.size() == 0):
			return None
		else:
			return self.recursiveMinimum(self.__root)
	
	def recursiveMinimum(self,node):
		if(not(node.getLeft().isEmpty())):
			return self.recursiveMinimum(node.getLeft())
		else:
			return node
	
	
	def predecessor(self,element):
		''' Return value of predecessor element of parameter element , or None if parameter element not found
		    or parameter element is lesser element in the tree
		
		   Args:
			element(optional) : search predecessor of this element
		
		   Returns:
			element(optional/None) : predecessor value of parameter element  / None			
		'''
		foundNode = self.search(element)
		if(foundNode.isEmpty()):
			return None
		elif(not(foundNode.getLeft().isEmpty())):
			return self.recursiveMaximum(foundNode.getLeft())
		else:
			parent = foundNode.getParent()
			while(parent != None and not(foundNode.getData() == parent.getRight().getData())):
				parent = parent.getParent()
				foundNode = foundNode.getParent()
			return parent
	
	def sucessor(self,element):
                ''' Return value of sucessor element of parameter element , or None if parameter element not found
                    or parameter element is larger element in the tree
                
                   Args:
                        element(optional) : search sucessor of this element
                
                   Returns:
                        element(optional/None) : sucessor value of parameter element  / None                 
                '''

		foundNode = self.search(element)
		if(foundNode.isEmpty()):
			return None
		elif(not(foundNode.getRight().isEmpty())):
			return self.recursiveMinimum(foundNode.getRight())
		else:
			parent = foundNode.getParent()
			while(parent != None and not(foundNode.getData() == parent.getLeft().getData())):
				parent = parent.getParent()
				foundNode = foundNode.getParent()
			return parent
	def getRoot(self):
		return self.__root
	
	def toArrayPreOrder(self):
		''' Return list structure with elements in order ->(node,left,right)
		
	            Returns:
			result[optional] : elements in order ->(node,left,right)
		'''
		result = []
		self.__recursivePreOrder(result,self.__root)
		return result
	def __recursivePreOrder(self,array,node):
		if(not(node.isEmpty())):
			array.append(node.getData())
			self.__recursivePreOrder(array,node.getLeft())
			self.__recursivePreOrder(array,node.getRight())
			
	
	def toArrayOrder(self):
		''' Return list structure with elements in order ->(left,node,right)
		
		    Returns:
			result[optional] : elements in order ->(left,node,right)
		'''
		result = []
		self.__recursiveOrder(result,self.__root)
		return result
	def __recursiveOrder(self,array,node):
		if(not(node.isEmpty())):
			self.__recursiveOrder(array,node.getLeft())
			array.append(node.getData())
			self.__recursiveOrder(array,node.getRight())
	
	
	
	def toArrayPostOrder(self):
                ''' Return list structure with elements in order ->(left,right,node)
                
                    Returns:
                        result[optional] : elements in order ->(left,right,node)
                '''
		result = []
		self.__recursivePostOrder(result,self.__root)
		return result
	def __recursivePostOrder(self,array,node):
		if(not(node.isEmpty())):
			self.__recursivePostOrder(array,node.getLeft())
			self.__recursivePostOrder(array,node.getRight())
			array.append(node.getData())
	
	def setRoot(self,newRoot):
		self.__root = newRoot
			
