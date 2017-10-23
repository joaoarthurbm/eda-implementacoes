#coding: utf-8
import sys,os
sys.path.append("../")
import unittest
from BST import BinarySearchTree
from Node import Node
from random import randint

class BSTTest(unittest.TestCase):

	def setUp(self):
		self.bst = BinarySearchTree()
		self.NIL = Node()
		


	def testSearch(self):
		self.assertEqual(self.NIL,self.bst.search(None))
		self.assertEqual(self.NIL,self.bst.search(randint(-100,1000)))
		self.bst.insert(9)
		self.assertEqual(9,self.bst.search(9).getData())
		self.assertEqual(self.NIL,self.bst.search(15))
		self.bst.insert(10)
		self.bst.insert(15)
		self.bst.insert(30)
		self.bst.insert(7)
		self.assertEqual(10,self.bst.search(10).getData())
		self.assertEqual(15,self.bst.search(15).getData())
		self.assertEqual(30,self.bst.search(30).getData())
		self.assertEqual(7,self.bst.search(7).getData())
		self.assertEqual(self.NIL,self.bst.search(randint(31,1000)))

		
	def testInsert(self):
		self.assertEqual(True,self.bst.isEmpty())
		self.bst.insert(None)
		self.assertEqual(True,self.bst.isEmpty())
		self.bst.insert(10)
		self.assertEqual(False,self.bst.isEmpty())
		self.bst.insert(20)
		self.bst.insert(30)
		self.bst.insert(5)
		self.bst.insert(7)
		self.bst.insert(3)
		self.bst.insert(15)
		self.bst.insert(25)
		
		self.assertEqual(10,self.bst.getRoot().getData())
		self.assertEqual(20,self.bst.getRoot().getRight().getData())
		self.assertEqual(5,self.bst.getRoot().getLeft().getData())
		self.assertEqual(7,self.bst.getRoot().getLeft().getRight().getData())
		self.assertEqual(3,self.bst.getRoot().getLeft().getLeft().getData())
		self.assertEqual(15,self.bst.getRoot().getRight().getLeft().getData())
		self.assertEqual(30,self.bst.getRoot().getRight().getRight().getData())
		self.assertEqual(25,self.bst.getRoot().getRight().getRight().getLeft().getData())
		
		#test insert repeated element
		size = self.bst.size()
		self.bst.insert(25)
		self.assertEqual(size,self.bst.size())

	def testRemove(self):
		self.assertEqual(True,self.bst.isEmpty())
		self.bst.insert(10)
		self.assertEqual(False,self.bst.isEmpty())
                self.bst.insert(20)
                self.bst.insert(30)
                self.bst.insert(5)
                self.bst.insert(7)
                self.bst.insert(3)
                self.bst.insert(15)
                self.bst.insert(25)
		
		self.bst.remove(10)
		self.assertEqual(15,self.bst.getRoot().getData())
		self.assertEqual(7,self.bst.size())
		self.assertEqual(3,self.bst.height())
		self.assertEqual(self.NIL,self.bst.search(10))	
		self.bst.remove(30)
		self.assertEqual(self.NIL,self.bst.sucessor(25))
		self.assertEqual(25,self.bst.maximum().getData())
		self.assertEqual(self.NIL,self.bst.search(30))
		self.assertEqual(2,self.bst.height())
		self.bst.remove(5)
                self.assertEqual(7,self.bst.sucessor(3))
                self.assertEqual(self.NIL,self.bst.search(5))
                self.assertEqual(2,self.bst.height())
		self.bst.remove(20)
                self.assertEqual(25,self.bst.sucessor(15))
                self.assertEqual(15,self.bst.predecessor(25))
                self.assertEqual(self.NIL,self.bst.search(20))
                self.assertEqual(2,self.bst.height())
		self.bst.remove(15)
		self.assertEqual(25,self.bst.getRoot().getData())
		self.assertEqual(self.NIL,self.bst.sucessor(25))
		self.assertEqual(2,self.bst.height())
		self.bst.remove(7)
		self.assertEqual(25,self.bst.sucessor(3))
		self.assertEqual(3,self.bst.predecessor(25))
		self.assertEqual(1,self.bst.height())
		self.assertEqual(2,self.bst.size())
		self.bst.remove(3)
                self.assertEqual(self.NIL,self.bst.sucessor(25))
                self.assertEqual(self.NIL,self.bst.predecessor(25))
                self.assertEqual(0,self.bst.height())
                self.assertEqual(1,self.bst.size())
		self.bst.remove(25)
		self.assertEqual(True,self.bst.isEmpty())





	def testMaximum(self):
		#Max empty bst
		self.assertEqual(True,self.bst.isEmpty())
		self.assertEqual(self.NIL,self.bst.maximum())

		self.bst.insert(10)
		self.assertEqual(False,self.bst.isEmpty())
		self.assertEqual(10,self.bst.maximum().getData())
		for i in range(10,50):
			self.bst.insert(i)
			self.assertEqual(i,self.bst.maximum().getData())
	

		self.bst = BinarySearchTree()
		self.assertEqual(True,self.bst.isEmpty())
		testList = [randint(50,1000) for i in range(30)]
		for i in range(len(testList)):
			self.bst.insert(testList[i])
		self.assertEqual(max(testList),self.bst.maximum().getData())
	
	
	def testMinimum(self):
		#Min empty bst
		self.assertEqual(True,self.bst.isEmpty())
		self.assertEqual(self.NIL,self.bst.minimum())	
		self.bst.insert(100)
		self.assertEqual(False,self.bst.isEmpty())
		self.assertEqual(100,self.bst.minimum().getData())
		
		for i in range(50,9,-1):
			self.bst.insert(i)
			self.assertEqual(i,self.bst.minimum().getData())
		self.bst = BinarySearchTree()
		self.assertEqual(True,self.bst.isEmpty())
		testList = [randint(-1000,1000) for i in range(50)]
		for i in range(len(testList)):
			self.bst.insert(testList[i])
		self.assertEqual(min(testList),self.bst.minimum().getData())
	
	
	def testSucessor(self):
		#test sucessor empty bst
		self.assertEqual(True,self.bst.isEmpty())
		self.assertEqual(self.NIL,self.bst.sucessor(randint(-100,10000)))
		
		#test sucessor None parameter
		self.assertEqual(self.NIL,self.bst.sucessor(None))
		
		self.bst.insert(10)
		self.assertEqual(self.NIL,self.bst.sucessor(10))
		self.bst.insert(20)
		self.bst.insert(30)
		self.bst.insert(5)
		self.bst.insert(7)
		self.bst.insert(100)
		self.bst.insert(3)
		self.bst.insert(15)
		self.bst.insert(25)

		self.assertEqual(5,self.bst.sucessor(3))
		self.assertEqual(7,self.bst.sucessor(5))
		self.assertEqual(10,self.bst.sucessor(7))
		self.assertEqual(15,self.bst.sucessor(10))
		self.assertEqual(20,self.bst.sucessor(15))
		self.assertEqual(25,self.bst.sucessor(20))
		self.assertEqual(30,self.bst.sucessor(25))
		self.assertEqual(100,self.bst.sucessor(30))
		self.assertEqual(self.NIL,self.bst.sucessor(100))


	def testPredecessor(self):
                #test sucessor empty bst
                self.assertEqual(True,self.bst.isEmpty())
                self.assertEqual(self.NIL,self.bst.predecessor(randint(-100,10000)))

                #test sucessor None parameter
                self.assertEqual(self.NIL,self.bst.predecessor(None))

                self.bst.insert(10)
                self.assertEqual(self.NIL,self.bst.predecessor(10))
                self.bst.insert(20)
                self.bst.insert(30)
                self.bst.insert(5)
                self.bst.insert(7)
                self.bst.insert(100)
                self.bst.insert(3)
                self.bst.insert(15)
                self.bst.insert(25)

                self.assertEqual(30,self.bst.predecessor(100))
                self.assertEqual(25,self.bst.predecessor(30))
                self.assertEqual(20,self.bst.predecessor(25))
                self.assertEqual(15,self.bst.predecessor(20))
                self.assertEqual(10,self.bst.predecessor(15))
                self.assertEqual(7,self.bst.predecessor(10))
                self.assertEqual(5,self.bst.predecessor(7))
                self.assertEqual(3,self.bst.predecessor(5))
		self.assertEqual(self.NIL,self.bst.predecessor(3))
		
		
	def testHeight(self):
		self.assertEqual(-1,self.bst.height())
		self.bst.insert(10)
		self.assertEqual(0,self.bst.height())
		self.bst.insert(20)
		self.assertEqual(1,self.bst.height())
		self.bst.insert(5)
		self.assertEqual(1,self.bst.height())
		self.bst.insert(15)
		self.assertEqual(2,self.bst.height())
		self.bst.insert(7)
		self.bst.insert(6)
		self.assertEqual(3,self.bst.height())
		self.bst.insert(3)
		self.bst.insert(30)
		self.assertEqual(3,self.bst.height())

	def testToArrayOrder(self):
		self.bst.insert(10)
                self.bst.insert(20)
                self.bst.insert(30)
                self.bst.insert(5)
                self.bst.insert(7)
                self.bst.insert(100)
                self.bst.insert(3)
                self.bst.insert(15)
                self.bst.insert(25)
		
		result = [3,5,7,10,15,20,25,30,100]
		self.assertEqual(result,self.bst.toArrayOrder())
		
	
	def testToArrayPreOrder(self):
                self.bst.insert(10)
                self.bst.insert(20)
                self.bst.insert(30)
                self.bst.insert(5)
                self.bst.insert(7)
                self.bst.insert(100)
                self.bst.insert(3)
                self.bst.insert(15)
                self.bst.insert(25)

                result = [10,5,3,7,20,15,30,25,100]
                self.assertEqual(result,self.bst.toArrayPreOrder())

	def testToArrayPostOrder(self):
                self.bst.insert(10)
                self.bst.insert(20)
                self.bst.insert(30)
                self.bst.insert(5)
                self.bst.insert(7)
                self.bst.insert(100)
                self.bst.insert(3)
                self.bst.insert(15)
                self.bst.insert(25)

                result = [3,7,5,15,25,100,30,20,10]
                self.assertEqual(result,self.bst.toArrayPostOrder())

	
if __name__ == "__main__":
	unittest.main()
