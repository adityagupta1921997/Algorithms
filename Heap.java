public class Heap {
    public int[] array;
    public int count;
    public int capacity;
    public int heap_type;

    public Heap(int capacity, int heap_type){
        this.heap_type = heap_type;
        this.count =0;
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public int Parent(int i){
        if(i<=0 || i>=this.count)
            return -1;
        return i-1/2;
    }

    public int leftChild(int i){
        int left = 2*i + 1;
        if(left>=this.count)
            return -1;
        return left;
    }

    public int rightChild(int i){
        int right = 2*i + 2;
        if(right>=this.count)
            return -1;
        return right;
    }

    public int getMaximum(){
        if(this.count==0)
            return -1;
        return this.array[0];
    }

    //Heapifying the element at location i;
    public void percolateDown(int i){
        int l,r,max,temp;
        l = leftChild(i);
        r = rightChild(i);
        if(l!=-1 && this.array[l] > this.array[i])
            max = l;
        else
            max = i;
        if(r!=-1 && this.array[r] > this.array[i])
            max = r;
        if(max!=i){ //Swap this.array[i] and this.array[max]
            temp = this.array[i];
            this.array[i] = this.array[max];
            this.array[max] = temp;
            percolateDown(max);
        }
    }

    //Deleting an element
    int deleteMax(){
        if(this.count == 0)
            return -1;
        int data = this.array[0];
        this.array[0] = this.array[this.count-1];
        this.count--; //reducing the heap size
        percolateDown(0);
        return data;
    }

    //Inserting an element
    public void insert(int data){
        int i;
        if(this.count == this.capacity)
            resizeHeap();
        this.count++;
        i = this.count-1;
        while(i>=0 && data > this.array[(i-1)/2]){
            this.array[i] = this.array[(i-1)/2];
            i = i-1/2;
        }
        this.array[i] = data;
    }

    public void resizeHeap(){
        int[] array_old = new int[this.capacity];
//        System.arraycopy(this.array, 0, array_old, this.count-1);
        array_old = this.array;
        this.array = new int[this.capacity*2];
        if(this.array == null){
            System.out.println("Memory Error");
            return;
        }
        for (int i=0; i<this.capacity;i++)
            this.array[i] = array_old[i];
        this.capacity *=2;
        array_old = null;
    }

    //Destroying Heap
    public void destroyHeap(){
        this.count = 0;
        this.array = null;
    }

    //Build Heap
    public void buildHeap(Heap h, int[] A, int n){
        if(h==null)
            return;
        while (n > this.capacity)
            h.resizeHeap();
        for(int i=0; i<n; i++)
            h.array[i] = A[i];
        this.count = n;
        for(int i =(n-1)/2; i>=0; i--)
            h.percolateDown(i);
    }

    //HeapSort
    public void heapSort(int[] A, int n){
        Heap h = new Heap(n,0);
        int old_size, i, temp;
        buildHeap(h, A, n);
        old_size = h.count;
        for(i=n-1; i>0; i--){ // h.array[0] is the largest element
            temp = h.array[0];
            h.array[0] = h.array[h.count-1];
            h.count--;
            h.percolateDown(0);
        }
        h.count = old_size;
    }
}
