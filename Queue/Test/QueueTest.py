import sys,os
sys.path.append("../")
import unittest
from CircularQueue import CircularQueue
from SimpleQueue import SimpleQueue


__author__ = "Ionesio Junior"

class QueueTest(unittest.TestCase):
	
	
	def setUp(self):
		self.simpleQueue = SimpleQueue(10)
		self.circularQueue = CircularQueue(10)
	
	
	
	def testIsEmpty(self):
		''' Test empty queue cases '''
		self.assertEqual(True,self.simpleQueue.isEmpty())
		self.assertEqual(True,self.circularQueue.isEmpty())
	
		self.simpleQueue.enqueue(11)
		self.circularQueue.enqueue(12)
		
		self.assertEqual(False,self.simpleQueue.isEmpty())
		self.assertEqual(False,self.circularQueue.isEmpty())
	
		
		self.simpleQueue.dequeue()
		self.circularQueue.dequeue()
		
		self.assertEqual(True,self.simpleQueue.isEmpty())
		self.assertEqual(True,self.circularQueue.isEmpty())
	
	
	def testIsFull(self):
 	  	''' Test full queue cases '''
		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isFull())
	
		for i in range(10):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)
	
		self.assertEqual(True,self.simpleQueue.isFull())
		self.assertEqual(True,self.circularQueue.isFull())
	
		self.simpleQueue.dequeue()
		self.circularQueue.dequeue()
	
		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isFull())


	def testHead(self):
		''' Test head method (acess head element without remove any element) '''
		for i in range(10):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)
		
		self.assertEqual(True,self.simpleQueue.isFull())
		self.assertEqual(True,self.circularQueue.isFull())

		for i in range(5):
			self.assertEqual(0,self.simpleQueue.head())
			self.assertEqual(0,self.circularQueue.head())

		self.assertEqual(True,self.simpleQueue.isFull())
		self.assertEqual(True,self.circularQueue.isFull())				
		

	def testEnqueue(self):
		''' Test enqueue method (insert an element in tail of the queue) '''
		
		self.simpleQueue.enqueue(None)
		self.circularQueue.enqueue(None)
		#Default
		self.assertEqual(True,self.simpleQueue.isEmpty())
		self.assertEqual(True,self.circularQueue.isEmpty())

		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isFull())
	
		#Mid
		for i in range(5):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)
	
		self.assertEqual(False,self.simpleQueue.isEmpty())
		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isEmpty())
		self.assertEqual(False,self.circularQueue.isFull())
		
		#Final
		for i in range(5,10,1):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)
			self.assertEqual(0,self.simpleQueue.head())
			self.assertEqual(0,self.circularQueue.head())

	
		self.assertEqual(True,self.simpleQueue.isFull())
		self.assertEqual(True,self.circularQueue.isFull())
		self.assertEqual(False,self.simpleQueue.isEmpty())
		self.assertEqual(False,self.circularQueue.isEmpty())


	def testDequeue(self):
		''' Test dequeue method (remove first element inserted) First-in/First-out '''
		#Default
		self.assertEqual(True,self.simpleQueue.isEmpty())
		self.assertEqual(True,self.circularQueue.isEmpty())

		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isFull())
		

		#Full
		for i in range(10):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)
			self.assertEqual(0,self.simpleQueue.head())
			self.assertEqual(0,self.circularQueue.head())

	
		self.assertEqual(True,self.simpleQueue.isFull())
		self.assertEqual(True,self.circularQueue.isFull())
		self.assertEqual(False,self.simpleQueue.isEmpty())
		self.assertEqual(False,self.circularQueue.isEmpty())
		
		#Empty
		for i in range(10):
			self.assertEqual(i,self.simpleQueue.dequeue())
			self.assertEqual(i,self.circularQueue.dequeue())


		self.assertEqual(False,self.simpleQueue.isFull())
		self.assertEqual(False,self.circularQueue.isFull())
		self.assertEqual(True,self.simpleQueue.isEmpty())
		self.assertEqual(True,self.circularQueue.isEmpty())
	
	
	def testException(self):
		''' Test when exceptions should be raised '''
		#UnderflowException
		try:
			self.simpleQueue.dequeue()
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")
		
		try:
			self.circularQueue.dequeue()
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")
	


		#OverflowException
		for i in range(10):
			self.simpleQueue.enqueue(i)
			self.circularQueue.enqueue(i)

		
		something = 1
		try:
			self.simpleQueue.enqueue(something)
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")
	


		try:
			self.circularQueue.enqueue(something)
		except Exception:
			pass
		else:
			self.fail("Exception not raised!")


if __name__ == "__main__":
	unittest.main()
