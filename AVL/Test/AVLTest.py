#coding: utf-8

import sys,os
sys.path.append("../")
sys.path.append("../../BST/")

from AVL import AVL
from BST import BinarySearchTree
from Node import Node
import unittest
from math import *

__author__ = "Ionesio Junior"


class SplayTest(unittest.TestCase):
	
	
	def setUp(self):
		self.avl = AVL()
		self.NIL = Node()

	
	def __fillTree(self):
		for i in range(10):
			self.avl.insert(i)
	
	def testInit(self):
		self.assertEqual(True,self.avl.isEmpty())
		self.assertEqual(0,self.avl.size())
		self.assertEqual(-1,self.avl.height())
		
		self.assertEqual(self.NIL,self.avl.getRoot())
		
		self.assertEqual([],self.avl.toArrayOrder())
		self.assertEqual([],self.avl.toArrayPreOrder())
		self.assertEqual([],self.avl.toArrayPostOrder())
		
		self.assertEqual(self.NIL,self.avl.search(12))
		self.assertEqual(self.NIL,self.avl.search(-23))
		self.assertEqual(self.NIL,self.avl.search(0))
		
		
		self.assertEqual(self.NIL,self.avl.maximum())
		self.assertEqual(self.NIL,self.avl.minimum())
		
		self.assertEqual(None,self.avl.sucessor(12))
		self.assertEqual(None,self.avl.sucessor(-23))
		self.assertEqual(None,self.avl.sucessor(0))
	
		self.assertEqual(None,self.avl.predecessor(12))
		self.assertEqual(None,self.avl.predecessor(-23))
		self.assertEqual(None,self.avl.predecessor(0))


	def testMinMax(self):
		self.avl.insert(6)
		self.assertEqual(6,self.avl.minimum().getData())
		self.assertEqual(6,self.avl.maximum().getData())
	
                self.avl.insert(23)
                self.assertEqual(6,self.avl.minimum().getData())
                self.assertEqual(23,self.avl.maximum().getData())

                self.avl.insert(-34)
                self.assertEqual(-34,self.avl.minimum().getData())
                self.assertEqual(23,self.avl.maximum().getData())

                self.avl.insert(5)
                self.assertEqual(-34,self.avl.minimum().getData())
                self.assertEqual(23,self.avl.maximum().getData())


                self.avl.insert(9)
                self.assertEqual(-34,self.avl.minimum().getData())
                self.assertEqual(23,self.avl.maximum().getData())


	def testSucessorPredecessor(self):
		self.__fillTree()
		self.assertEqual(None,self.avl.predecessor(15))
		self.assertEqual(3,self.avl.sucessor(2))
		
		self.assertEqual(3,self.avl.predecessor(4))
		self.assertEqual(5,self.avl.sucessor(4))


	def testSize(self):
		self.__fillTree()
		size  = 10
		self.assertEqual(size,self.avl.size())
		
		while(not(self.avl.isEmpty())):
			self.avl.remove(self.avl.getRoot().getData())
			size -= 1;
			self.assertEqual(size,self.avl.size())
	
	def testHeight(self):
		self.__fillTree()
		preOrder = [3,1,0,2,7,5,4,6,8,9]
		self.assertEqual(preOrder,self.avl.toArrayPreOrder())
		
		self.assertEqual(3,self.avl.height())
		self.avl.remove(0)
		self.assertEqual(3,self.avl.height())
		self.avl.remove(2)
		self.assertEqual(3,self.avl.height())
		self.avl.remove(1)
		self.assertEqual(3,self.avl.height())
		
		self.assertEqual([7,5,3,4,6,8,9],self.avl.toArrayPreOrder())


	
	def testBruteHeight(self):
		for i in range(1,3000,1):
			self.avl.insert(i)
			self.assertTrue(self.avl.height() <= self.__maxPossibleHeight(i))
			self.assertEqual(i,self.avl.size())
			self.assertEqual(None,self.avl.getRoot().getParent())
	

	def __log(self,n):
		return log(n,10) / log(2,10)
	
	def __maxPossibleHeight(self,index):
		if(index == 0):
			return 0
		else:
			return int(2 * self.__log(index))
	

	def testLeft(self):
		self.avl.insert(2)
		self.avl.insert(1)
		self.avl.insert(0)
		self.assertEqual(1,self.avl.height())
		self.assertEqual(1,self.avl.getRoot().getData())
		self.assertEqual(0,self.avl.getRoot().getLeft().getData())
		self.assertEqual(2,self.avl.getRoot().getRight().getData())
		self.assertEqual(self.avl.getRoot().getLeft().getParent() ,self.avl.getRoot())
		self.assertEqual(self.avl.getRoot().getRight().getParent() ,self.avl.getRoot())
		self.assertEqual(None,self.avl.getRoot().getParent())


	def testRight(self):
                self.avl.insert(1)
                self.avl.insert(2)
                self.avl.insert(3)
                self.assertEqual(1,self.avl.height())
                self.assertEqual(2,self.avl.getRoot().getData())
                self.assertEqual(1,self.avl.getRoot().getLeft().getData())
                self.assertEqual(3,self.avl.getRoot().getRight().getData())
                self.assertEqual(self.avl.getRoot().getLeft().getParent() ,self.avl.getRoot())
                self.assertEqual(self.avl.getRoot().getRight().getParent() ,self.avl.getRoot())
                self.assertEqual(None,self.avl.getRoot().getParent())


	def testRightLeft(self):
		self.avl.insert(5)
		self.avl.insert(6)
		self.avl.insert(0)
		self.avl.insert(1)
		self.avl.insert(-1)
		self.avl.insert(2)
		
		self.assertEqual(2,self.avl.height())
		self.assertEqual(1,self.avl.getRoot().getData())
		self.assertEqual(0,self.avl.getRoot().getLeft().getData())
		self.assertEqual(5,self.avl.getRoot().getRight().getData())
		self.assertEqual(-1,self.avl.getRoot().getLeft().getLeft().getData())
		self.assertEqual(2,self.avl.getRoot().getRight().getLeft().getData())
		self.assertEqual(6,self.avl.getRoot().getRight().getRight().getData())
		
		self.assertEqual([1,0,-1,5,2,6],self.avl.toArrayPreOrder())


	def testLeftRight(self):
                self.avl.insert(3)
                self.avl.insert(2)
                self.avl.insert(7)
                self.avl.insert(5)
                self.avl.insert(8)
                self.avl.insert(4)

                self.assertEqual(2,self.avl.height())
                self.assertEqual(5,self.avl.getRoot().getData())
                self.assertEqual(3,self.avl.getRoot().getLeft().getData())
                self.assertEqual(7,self.avl.getRoot().getRight().getData())
                self.assertEqual(2,self.avl.getRoot().getLeft().getLeft().getData())
                self.assertEqual(4,self.avl.getRoot().getLeft().getRight().getData())
                self.assertEqual(8,self.avl.getRoot().getRight().getRight().getData())

                self.assertEqual([5,3,2,4,7,8],self.avl.toArrayPreOrder())



if __name__ == "__main__":
	unittest.main()
