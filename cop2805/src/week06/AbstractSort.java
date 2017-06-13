package week06;

/**
 * Base class for sorting routines
 * 
 * @author scottl
 * 
 */
public abstract class AbstractSort
{
    /**
     * Constructor
     * 
     * @param name
     *            Name of the sort routine
     */
    public AbstractSort(String name, int[] list)
    {
        m_sortName = name;
        m_list = list;
    }

    /**
     * Sorts the data
     */
    public abstract void sort();

    /**
     * The sort routine name
     * 
     * @return Name of the sort routine
     */
    public String getName()
    {
        return m_sortName;
    }

    public int[] getList()
    {
        return m_list;
    }

    /** The name of the sort routine */
    private String m_sortName;
    protected int[] m_list;
}
