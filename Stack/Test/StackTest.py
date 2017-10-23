import sys,os
import unittest
sys.path.append("../")
from Stack import Stack

__author__ = "Ionesio Junior"


class StackTest(unittest.TestCase):
	
	
	def setUp(self):
		self.stack1 = Stack(6)
		self.stack2 = Stack(10)



	def testIsEmpty(self):
		''' Test Empty stack case '''
		self.assertEqual(True,self.stack1.isEmpty())
		self.assertEqual(True,self.stack2.isEmpty())
		
		self.stack1.push(10)
		self.assertEqual(False,self.stack1.isEmpty())
		self.stack1.pop()
		self.assertEqual(True,self.stack1.isEmpty())
	
	
	def testIsFull(self):
		''' Test Full stack case '''
		self.assertEqual(False,self.stack1.isFull())
		self.assertEqual(False,self.stack2.isFull())
		
		for i in range(6):
			self.stack1.push(i)
	
		for i in range(10):
			self.stack2.push(i)
		
		self.assertEqual(True,self.stack1.isFull())
		self.assertEqual(True,self.stack2.isFull())
		
		self.stack1.pop()
		self.stack2.pop()

		self.assertEqual(False,self.stack1.isFull())
		self.assertEqual(False,self.stack2.isFull())


	def testTop(self):
		''' Test top method (acess top of stack without remove any element) '''
		self.assertEqual(None,self.stack1.top())
		self.assertEqual(None,self.stack2.top())
		
		for i in range(10):
			if(i < 6):
				self.stack1.push(i)
				self.stack2.push(i)
				self.assertEqual(i , self.stack1.top())
				self.assertEqual(i,self.stack2.top())
			else:
				self.stack2.push(i)
				self.assertEqual(i,self.stack2.top())


	def testPush(self):
		''' Test push method (insert some element  at the top of the stack) '''
		self.stack1.push(None)
		self.assertEqual(True,self.stack1.isEmpty())
		self.assertEqual(False,self.stack1.isFull())

		for i in range(6):
			self.stack1.push(i)
		
		self.assertEqual(False,self.stack1.isEmpty())
		self.assertEqual(True,self.stack1.isFull())
	
		self.assertEqual(5,self.stack1.top())
	
	
	def testPop(self):
		''' Test pop method (remove the last element inserted) First-in / Last-out '''
		for i in range(10):
			self.stack2.push(i)
		
		self.assertEqual(True,self.stack2.isFull())
		self.assertEqual(False,self.stack2.isEmpty())

		for i in range(9,-1,-1):
			self.assertEqual(i,self.stack2.pop())

		self.assertEqual(True,self.stack2.isEmpty())
		self.assertEqual(False,self.stack2.isFull())
			
	
	def testException(self):
		''' Test when exceptions should be raised '''
		for i in range(10):
			self.stack2.push(i)
		
		try:
			self.stack2.push(10)
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")
		
			
		try:
			self.stack1.pop()
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")



if __name__ == "__main__":
	unittest.main()		
