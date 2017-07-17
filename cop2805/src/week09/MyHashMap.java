package week09;
/**
 * @author Liang?
 */
import java.util.LinkedList;

public class MyHashMap<K, V> implements MyMap<K, V>
{
	/** 
	 * Construct a map with the default capacity and load factor 
	 * */
	public MyHashMap()
	{
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
	}

	/**
	 * Construct a map with the specified initial capacity and default load
	 * factor
	 * @param initialCapacity init cap for map
	 */
	public MyHashMap(int initialCapacity)
	{
		this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
	}

	/**
	 * Construct a map with the specified initial capacity and load factor
	 * @param initialCapacity initial capacity
	 * @param loadFactorThreshold load factor threshold
	 */
	@SuppressWarnings("unchecked")
	public MyHashMap(int initialCapacity, float loadFactorThreshold)
	{
		if( initialCapacity > MAXIMUM_CAPACITY)
		{
			this.capacity = MAXIMUM_CAPACITY;
		}
		else
		{
			this.capacity = trimToPowerOf2(initialCapacity);
		}
		this.capacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		table = new LinkedList[capacity];
	}
	
	/**
	 * trims to power of 2
	 * @param initialCapacity init cap
	 * @return in return trimmed to p2
	 */
	private int trimToPowerOf2(int initialCapacity)
	{
		int capacity = 1;
//		h ^= (h >>> 20) ^ (h >>> 12);
//		return h ^ (h >>>7) ^ (h >>> 4);
		while( capacity < initialCapacity)
		{
			capacity <<= 1;
		}
		
		return capacity;
	}

	/** 
	 * Remove all of the entries from this map 
	 * */
	public void clear()
	{
		size = 0;
		removeEntries();
	}


	/** 
	 * check if map contains key
	 * @param key - key to check map for
	 * @return true if the specified key is in the map 
	 * */
	
	public boolean containsKey(K key)
	{
		if(get(key) != null)
			return true;
		else
			return false;
	}

	/**
	 * check if map contains value
	 * @param value to check map for
	 * @return true if this map contains the specified value 
	 * */
	public boolean containsValue(V value)
	{
		for(int i = 0; i < capacity; i++)
		{
			if(table[i] != null)
			{
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket)
				{
					if(entry.getValue().equals(value))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	/** 
	 * set entry
	 * @return a set of entries in the map 
	 * */
	public java.util.Set<MyMap.Entry<K, V>> entrySet()
	{
		java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();

		for(int i = 0; i < capacity; i++)
		{
			if(table[i] != null)
			{
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket)
				{
					set.add(entry);
				}
			}
		}

		return set;
	}

	/**
	 * gets value at key
	 * @param key key to get 
	 * @return the first value that matches the specified key 
	 * */

	public V get(K key)
	{
		// Perform linear probing
		int bucketIndex = hash(key.hashCode());

		if(table[bucketIndex] != null)
		{
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket)
			{
				if(entry.getKey().equals(key))
				{
					return entry.getValue();
				}
			}
		}

		return null;
	}

	/** return all values for the specified key in this map */
	// public java.util.Set<V> getAll(K key)
	// {
	// java.util.Set<V> set = new java.util.HashSet<V>();
	//
	// for(int i = 0; i < capacity; i++)
	// if(table[i] != null && table[i].key.equals(key))
	// set.add(table[i].value);
	//
	// return set;
	// }

	/** 
	 * checks if map is empty 
	 * @return true if this map contains no entries 
	 * */
	public boolean isEmpty()
	{
		return size == 0;
	}

	/**
	 * get keys in map 
	 * @return a set consisting of the keys in this map 
	 * */
	public java.util.Set<K> keySet()
	{
		java.util.Set<K> set = new java.util.HashSet<K>();

		for(int i = 0; i < capacity; i++)
		{
			LinkedList<Entry<K, V>> bucket = table[i];
			for(Entry<K, V> entry : bucket)
			{
				set.add(entry.getKey());
			}
		}

		return set;
	}

	// /** Add an entry (key, value) into the map */
	// public V put(K key, V value)
	// {
	// if(size >= capacity * loadFactorThreshold)
	// {
	// if(capacity == MAXIMUM_CAPACITY)
	// {
	// throw new RuntimeException("Exceeding maximum capacity");
	// }
	//
	// rehash();
	// }
	//
	// int i = hash(key.hashCode());
	//
	// while(table[i] != null && table[i].key != null)
	// {
	// i = (i + 1) % table.length;
	// }
	//
	// // Add an entry (key, value) to the table
	// table[i] = new MyMap.Entry<K, V>(key, value);
	//
	// size++; // Increase size
	//
	// return value;
	// }

	/**
	 * puts key pair in map
	 * @param key key at which to insert
	 * @param value to insert at key
	 * @return v return object v
	 */
	public V put(K key, V value)
	{
		if(get(key) != null)
		{
			int bucketIndex = hash(key.hashCode());
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket)
			{
				if(entry.getKey().equals(key))
				{
					V oldValue = entry.getValue();
					entry.value = value;
					return oldValue;
				}
			}
		}

		if(size >= capacity * loadFactorThreshold)
		{
			if(capacity == MAXIMUM_CAPACITY)
			{
				throw new RuntimeException("Exceeding maximum capacity");
			}
		}

		int bucketIndex = hash(key.hashCode());

		if(table[bucketIndex] == null)
		{
			table[bucketIndex] = new LinkedList<Entry<K, V>>();
		}

		table[bucketIndex].add(new MyMap.Entry<K, V>(key, value));
		size++;

		return value;

	}

	/** 
	 * Remove the element for the specified key 
	 * @param key - key to remove
	 * */
	public void remove(K key)
	{
		int bucketIndex = hash(key.hashCode());

		if(table[bucketIndex] != null)
		{
			LinkedList<Entry<K, V>> bucket = table[bucketIndex];
			for(Entry<K, V> entry : bucket)
			{
				if(entry.getKey().equals(key))
				{
					bucket.remove(entry);
					size--;
					break;
				}
			}
		}

		// while(table[i] != null
		// && (table[i].key == null || !table[i].key.equals(key)))
		// i = (i + 1) % table.length;
		//
		// if(table[i] != null && table[i].key.equals(key))
		// {
		// // A special marker Entry(null, null) is placed for the deleted
		// // entry
		// table[i] = new MyMap.Entry<K, V>(null, null);
		// size--;
		// }
	}

	/**
	 * get map size 
	 * @return the number of mappings in this map 
	 * */
	public int size()
	{
		return size;
	}

	/**
	 * get values from map 
	 * @return a set consisting of the values in this map 
	 * */
	public java.util.Set<V> values()
	{
		java.util.Set<V> set = new java.util.HashSet<V>();

		for(int i = 0; i < capacity; i++)
		{
			if(table[i] != null)
			{
				LinkedList<Entry<K, V>> bucket = table[i];
				for(Entry<K, V> entry : bucket)
				{
					set.add(entry.getValue());
				}
			}
		}

		return set;
	}

	/**
	 *  Hash function 
	 *  @return hashcode % capacity
	 *  */
	private int hash(int hashCode)
	{
		//return hashCode % capacity;
		return supplementalHash(hashCode) & (capacity - 1);
	}

	/** 
	 * Ensure the hashing is evenly distributed 
	 * @return int - no idea what this does 
	 * */
	private static int supplementalHash(int h)
	{
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

	/** 
	 * Remove all entries from each bucket 
	 * */
	private void removeEntries()
	{
		for(int i = 0; i < capacity; i++)
			table[i] = null;
	}

	/** 
	 * Rehash the map 
	 * */
	@SuppressWarnings({ "unused", "unchecked" })
	private void rehash()
	{
		java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
		capacity <<= 1; // Double capacity
		table = new LinkedList[capacity]; // Create a new hash table

		size = 0; // Clear size

		for(Entry<K, V> entry : set)
		{
			put(entry.getKey(), entry.getValue()); // Store to new table
		}
	}

	@Override 
	/**
	* to string method  
	* @return a string representation for this map 
	*/
	public String toString()
	{
		StringBuilder builder = new StringBuilder("[");

		for(int i = 0; i < capacity; i++)
		{
			if(table[i] != null && table[i].size() > 0)
			{
				for(Entry<K, V> entry : table[i])
				{
					builder.append(entry);
				}
			}
		}

		return builder.append("]").toString();
	}

	// Define the default hash table size.
	private static int DEFAULT_INITIAL_CAPACITY = 4;

	// Define the maximum hash table size. 1 << 30 is same as 2^30
	private static int MAXIMUM_CAPACITY = 1 << 30;

	// Current hash table capacity.
	private int capacity;

	// Define default load factor
	private static float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

	// Specify a load factor used in the hash table
	private float loadFactorThreshold;

	// The number of entries in the map
	private int size = 0;

	// Hash table is an array with each cell that is a linked list
	LinkedList<MyMap.Entry<K, V>>[] table;
}
