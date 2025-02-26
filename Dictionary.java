import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dictionary<K, V> implements DictionaryInterface<K, V>{
	private Entry<K, V>[] dictionary;
	private int numOfEntries;
	private boolean integrityOk = false;
	private final static int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 1000;
	
	public Dictionary() {
		this(DEFAULT_CAPACITY);
	}
	
	public Dictionary(int initialCapacity) {
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		Entry<K, V>[]tempDictionary = (Entry<K, V>[])new Entry[initialCapacity];
		dictionary = tempDictionary;
		numOfEntries = 0;
		integrityOk = true;
	}
	
	@Override
	public V add(K key, V value) {
		if((key == null)|| (value == null)) {
			throw new IllegalArgumentException("Cannot add null to this dictionary");
		}else {
			V result = null;
			int keyIndex = locateIndex(key);
			if(keyIndex < numOfEntries) {
				result = dictionary[keyIndex].getValue();
				dictionary[keyIndex].setValue(value);
			}else {
				dictionary[numOfEntries] = new Entry<>(key, value);
				numOfEntries++;
			}
			return result;
		}
		
	}

	@Override
	public V remove(K key) {
		int keyIndex = locateIndex(key);
	    V result = null;
	    
	    if (keyIndex < numOfEntries) {
	        result = dictionary[keyIndex].getValue();
	        
	        // Shift entries to fill the removed entry's place
	        for (int i = keyIndex; i < numOfEntries - 1; i++) {
	            dictionary[i] = dictionary[i + 1];
	        }
	        
	        dictionary[numOfEntries - 1] = null; // Remove reference to the last entry
	        numOfEntries--;
	    }
	    
	    return result;
	}

	@Override
	public V getValue(K key) {
		int keyIndex = locateIndex(key);
	    if (keyIndex < numOfEntries) {
	        return dictionary[keyIndex].getValue();
	    }else
		return null;
	}

	@Override
	public boolean contains(K key) {
		int keyIndex = locateIndex(key);
	    return keyIndex < numOfEntries;
	}

	@Override
	public Iterator<K> getKeyIterator() {
		 return new Iterator<K>() {
		        private int currentIndex = 0;

		        @Override
		        public boolean hasNext() {
		            return currentIndex < numOfEntries;
		        }

		        @Override
		        public K next() {
		            if (hasNext()) {
		                return dictionary[currentIndex++].getKey();
		            } else {
		                throw new NoSuchElementException("No more elements in the dictionary");
		            }
		        }
		    };
	}

	@Override
	public Iterator<V> getValueIterator() {
		 return new Iterator<V>() {
		        private int currentIndex = 0;

		        @Override
		        public boolean hasNext() {
		            return currentIndex < numOfEntries;
		        }

		        @Override
		        public V next() {
		            if (hasNext()) {
		                return dictionary[currentIndex++].getValue();
		            } else {
		                throw new NoSuchElementException("No more elements in the dictionary");
		            }
		        }
		    };
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numOfEntries == 0;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return numOfEntries;
	}

	@Override
	public void clear() {
		 for (int i = 0; i < numOfEntries; i++) {
		        dictionary[i] = null;
		    }
		    numOfEntries = 0;
		
	}
	
	private class Entry<K, V>{
		private K key;
		private V value;
		private Entry(K searchKey, V dataValue) {
			key = searchKey;
			value = dataValue;
		}
	private K getKey() {
		return key;
	}
	private V getValue() {
		return value;
	}
	private void setValue(V dataValue) {
		value = dataValue;
	}
	}
	
	private int locateIndex(K key) {
		int index = 0;
		while ((index < numOfEntries)&& !key.equals(dictionary[index].getKey()))
			index++;
			return index;
		}
	
	private void checkCapacity(int capacity) {
		if(capacity > MAX_CAPACITY) {
			throw new IllegalStateException("Capacity exeeds "+ MAX_CAPACITY);
		}
	}
	
	

	

	

}
