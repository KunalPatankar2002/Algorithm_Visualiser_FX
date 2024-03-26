# SortingVisualiser

SortingVisualiser is a Java package that provides a simple graphical user interface for visualizing various sorting algorithms.

## Usage

The SortingVisualiser package offers a user-friendly interface for visualizing sorting algorithms. Here's how you can use it:

1. Run the `Sorting_Visualiser.exe` to launch the application on a Windows system.
2. Click on the sorting algorithm buttons provided (Quick Sort, Bubble Sort, Merge Sort, Heap Sort) to visualize the corresponding sorting algorithm.
3. Watch as the algorithm sorts a visual representation of an array of elements.

   #OR
1. Run the `demo\src\main\java\com\example\App.java` main class to launch the application.
2. Click on the sorting algorithm buttons provided (Quick Sort, Bubble Sort, Merge Sort, Heap Sort) to visualize the corresponding sorting algorithm.
3. Watch as the algorithm sorts a visual representation of an array of elements.

## Supported Algorithms

SortingVisualiser currently supports the following sorting algorithms:

- Quick Sort
- Bubble Sort
- Merge Sort
- Heap Sort

## Project Structure
```
├── demo/
|      ├── src/
|      |      └── main/
|      |      |      └── java/
|      |      |      |      └── com/
|      |      |      |      |      └── example/
|      |      |      |      |      │      └── App.java
|      |      |      |      │      |      ├── BubbleSort.java
|      |      |      |      │      |      ├── HeapSort.java
|      |      |      |      │      |      ├── MergeSort.java
|      │      |      |      |      |      └── QuickSort.java
|      |      |      |      └── module-info.java
|      ├── target/
|      |      └── classes\
|      |      ├── image\
|      |      ├── maven-archiver\
|      |      ├── Algorithm_Visualiser-1.0-SNAPSHOT.jar
|      |      └── Algorithm_Visualiser.exe
|      ├── tools/
|      |      └── warp-packer,exe
|      └── pom.xml
└── README.md
```


## Contributing

Contributions to SortingVisualiser are welcome! If you'd like to contribute, please follow these guidelines:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.

