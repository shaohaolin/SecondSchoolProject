package Lab8;

import java.util.Iterator;

public class HashingDictionary<K,V> implements DictionaryInterface<K,V>{
	
	private Entry <K,V>[] table; //array of unsorted entries
	private int currentsize; //number of entries
	private int locationsUsed; //number of locations used
	
	private final static int DEFAULT_CAPACITY = 31; // must be prime
	private final static double loadFactor = 0.75;
	private int threshold;
	
	public HashingDictionary(){
		this(DEFAULT_CAPACITY);
	} //end default constructor
	
	public HashingDictionary(int initialCapacity){
		int primesize = getNextPrime(initialCapacity);
		
		table = new Entry [primesize];
		currentsize = 0;
		locationsUsed = 0;
		threshold = (int)(primesize * loadFactor);
	} //end constructor
	
	
	@Override
	public V add(K key, V value) {
		// TODO Auto-generated method stub
		
		V result = null;
		
		int keyIndex = locateIndex(key);
		keyIndex = quadraticProbe(keyIndex, key);
		assert (keyIndex >=0) &&(keyIndex < table.length);
		if(keyIndex <currentsize){
			// key found; return and replace old value
			result = table[keyIndex].getValue();
			table[keyIndex].setValue(value);
		}
		
		else{
			if(isArrayfull()){
				rehash();
			}
			table[currentsize] = new Entry <K,V>  (key, value);
			currentsize++;
			locationsUsed++;
		} //end if
		
		return result;
	}

	private void rehash(){
		int oldCapacity = table.length;
		Entry<K,V>[] oldMap = table;
		
		int newCapacity = getNextPrime(oldCapacity + oldCapacity);
		table = new Entry[newCapacity];
		threshold = (int)(newCapacity * loadFactor);
		currentsize = 0;
		locationsUsed = 0;
		
		for(int i = 0; i<oldCapacity; i++){
			if (oldMap[i]!=null && oldMap[i].isIn()){
				add(oldMap[i].getKey(),oldMap[i].getValue());
			}
		}
		
	}
	
	boolean isArrayfull(){
		return currentsize >= threshold;
	} //end isArrayfull function
	
	private int locateIndex (K key){
		int index = 0;
		while ((index < currentsize) && !key.equals(table[index].getKey())){
			index++;
		}
		return index;
	} //end locateIndex
	
	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex<currentsize){
			result = table[keyIndex].getValue();
			table[keyIndex].setToRemoved();;
			currentsize--;
		}
		
		return result;
	}

	@Override
	public V getValue(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
		if ((keyIndex < currentsize) && key.equals(table[keyIndex].getKey())){
			result = table[keyIndex].getValue();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return getValue(key)!=null;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentsize == 0;
	}

	@Override
	public boolean isFull() {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return currentsize;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int index = table.length; index>0; index--){
			table[index] = null;
		}
		currentsize = 0;
		locationsUsed = 0;
	}
	
	private int getNextPrime(int integer){
		while(!isPrime(integer)){
			integer++;
		}
		return integer;
	}
	
	private boolean isPrime(int integer){
		if (integer == 1 || integer%2 == 0){
			return false;
		}
		
		for (int m = 3; m<Math.sqrt(integer); m+=2){
			if(integer%m == 0){
				return false;
			}
		}
		return true;
	}
	
	private int quadraticProbe(int index, K key){
		boolean found = false;
		int removedStateIndex = -1; //index of first location in removed state
		int i = 0;
		while (!found && (table[index] != null) && i<table.length){
			if (table[index].isIn()){
				if(key.equals(table[index].getKey())){
					found = true; // key found
				}
				else // follow probe sequence 
				{
					index = (index + i*i) % table.length;
					i++;
				}
			}
			else // skip entries that were removed
			{
				// save index of first location in removed state
				if(removedStateIndex == -1){
					index = (index + i*i) % table.length;
					i++;
				}
			} // end if
		} // end while 
		//	Assertion: either key or null is found at table[index]
		if (found || (removedStateIndex == -1)){
			return index;	//	index of either key or null
		}
		else { 
			return removedStateIndex;	//	index of an available location
		}
		
	}	//	end probe
	
	private class Entry <S,T>{
		private S key;
		private T value;
		private boolean inTable; //true if entry is in table
		
		private Entry (S searchKey, T dataValue) {
			key = searchKey;
			value = dataValue;
			inTable = true;
		} //end constructor
		
		private S getKey(){
			return key;
		} // end getKey
		
		private T getValue(){
			return value;
		} // end getValue
		
		private void setValue(T newValue){
			value = newValue;
		} //end SetValue
		
		private boolean isIn(){
			return inTable;
		} // end isIn
		
		private boolean isRemoved(){
			return !inTable;
		} // end isRemoved
		
		//state true means entry in use; false means entry not in use, ie delete
		
		private void setToRemoved(){
			key = null;
			value = null;
			inTable = false;
		} // end setToRemoved
		
		private void setToIn() // not used
		{
			inTable = true;
		} // end setToIn
	} //end Entry
}
