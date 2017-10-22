#coding : utf-8

import sys,os
sys.path.append("../")
sys.path.append('../../BST/')
import unittest
from Splay import Splay
from BST import BinarySearchTree
from Node import Node


__author__ = "Ionesio Junior"


class SplayTest(unittest.TestCase):
	
	def setUp(self):
		self.splay = Splay()
		self.NIL = Node()
	
	def testInit(self):
		self.assertEqual(True,self.splay.isEmpty())
		self.assertEqual(-1,self.splay.height())
		self.assertEqual(0,self.splay.size())
		self.assertEqual(self.NIL,self.splay.getRoot())
	
	def testInsert(self):
		self.splay.insert(5)
		self.assertEqual(1,self.splay.size())
		self.assertEqual(0,self.splay.height())
		self.assertEqual(False,self.splay.isEmpty())
	
		self.splay.insert(-15)
		self.assertEqual(-15,self.splay.getRoot().getData())
		self.assertEqual(2,self.splay.size())
		self.assertEqual(1,self.splay.height())
		
		self.splay.insert(0)
		self.assertEqual(0,self.splay.getRoot().getData())
		self.assertEqual(1,self.splay.height())
		self.assertEqual(-15,self.splay.getRoot().getLeft().getData())
		self.assertEqual(5,self.splay.getRoot().getRight().getData())
		
		self.splay.insert(25)
		self.assertEqual(25,self.splay.getRoot().getData())
		self.assertEqual(self.NIL,self.splay.getRoot().getRight())
		self.assertEqual(3,self.splay.height())
	
		self.splay.insert(3)
		self.assertEqual(3,self.splay.getRoot().getData())
		self.assertEqual(0,self.splay.getRoot().getLeft().getData())
		self.assertEqual(25,self.splay.getRoot().getRight().getData())
		self.assertEqual(5,self.splay.size())
		self.assertEqual(2,self.splay.height())
	
	def testRemove(self):
		self.splay.insert(5)		
                self.splay.insert(120)
                self.splay.insert(1)
                self.splay.insert(-100)
                
		self.splay.remove(1)
		self.assertEqual(3,self.splay.size())
		self.assertEqual(5,self.splay.getRoot().getData())
		self.assertEqual(1,self.splay.height())
		
		self.splay.remove(5)
		self.assertEqual(2,self.splay.size())
		self.assertEqual(120,self.splay.getRoot().getData())
		self.assertEqual(self.NIL,self.splay.getRoot().getRight())
		self.assertEqual(-100,self.splay.getRoot().getLeft().getData())
		self.assertEqual(1,self.splay.height())
		
		self.splay.remove(120)
		self.assertEqual(1,self.splay.size())
		self.assertEqual(-100,self.splay.getRoot().getData())
		self.assertEqual(self.NIL,self.splay.getRoot().getLeft())
		self.assertEqual(self.NIL,self.splay.getRoot().getRight())
		self.assertEqual(0,self.splay.height())
			
		self.splay.remove(500) #aleatory number not inserted in splay tree
		
		self.assertEqual(1,self.splay.size())
                self.assertEqual(-100,self.splay.getRoot().getData())
                self.assertEqual(self.NIL,self.splay.getRoot().getLeft())
                self.assertEqual(self.NIL,self.splay.getRoot().getRight())
                self.assertEqual(0,self.splay.height())
			
		self.splay.remove(-100)
		self.assertEqual(0,self.splay.size())
                self.assertEqual(self.NIL,self.splay.getRoot())
                self.assertEqual(self.NIL,self.splay.getRoot().getLeft())
                self.assertEqual(self.NIL,self.splay.getRoot().getRight())
                self.assertEqual(-1,self.splay.height())
		self.assertEqual(True,self.splay.isEmpty())

	
	def testSearchInsert(self):
		self.splay.insert(10)
		self.splay.insert(18)
		self.splay.insert(2)
		self.splay.insert(13)
		self.splay.insert(19)
		
		self.assertEqual(19,self.splay.getRoot().getData())
		self.splay.search(19)
		self.assertEqual(19,self.splay.getRoot().getData())
		self.assertEqual(4,self.splay.height())
		
		self.splay.search(10)
		self.assertEqual(10,self.splay.getRoot().getData())
		self.assertEqual(2,self.splay.height())	
		
		self.splay.insert(28)
		self.assertEqual(28,self.splay.getRoot().getData())
		self.assertEqual(4,self.splay.height())
	
	
		self.splay.insert(27)
		self.assertEqual(27,self.splay.getRoot().getData())
		self.assertEqual(4,self.splay.height())
		
		self.splay.insert(15)
		self.assertEqual(3,self.splay.height())
		self.assertEqual(15,self.splay.getRoot().getData())

		self.splay.insert(22)
		self.assertEqual(22,self.splay.getRoot().getData())
		self.assertEqual(3,self.splay.height())
		
		
		self.splay.search(18)
		self.assertEqual(3,self.splay.height())
		self.assertEqual(18,self.splay.getRoot().getData())

		
                self.splay.search(13)
                self.assertEqual(4,self.splay.height())
                self.assertEqual(13,self.splay.getRoot().getData())

                self.splay.search(2)
                self.assertEqual(6,self.splay.height())
                self.assertEqual(2,self.splay.getRoot().getData())

                self.splay.search(19)
                self.assertEqual(4,self.splay.height())
                self.assertEqual(19,self.splay.getRoot().getData())


                self.splay.search(29)
                self.assertEqual(5,self.splay.height())
                self.assertEqual(28,self.splay.getRoot().getData())

                self.splay.search(23)
                self.assertEqual(5,self.splay.height())
                self.assertEqual(22,self.splay.getRoot().getData())

                self.splay.search(9)
                self.assertEqual(4,self.splay.height())
                self.assertEqual(10,self.splay.getRoot().getData())

                self.splay.search(21)
                self.assertEqual(5,self.splay.height())
                self.assertEqual(22,self.splay.getRoot().getData())
	
                self.splay.search(None)
                self.assertEqual(5,self.splay.height())
                self.assertEqual(22,self.splay.getRoot().getData())

			
	
if __name__ == "__main__":
	unittest.main()
