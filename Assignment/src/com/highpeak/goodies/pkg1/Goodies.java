package com.highpeak.goodies.pkg1;

import java.io.*;
import java.util.*;
class Product{
  String name;
  int price;

  public Product(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Goodies
{
  public static void main(String[] args) throws IOException
  {
    File file=new File("C:\\Users\\sushanth\\workspacejava"
    		+ "\\Assignment\\src\\com\\highpeak\\goodies\\pkg1\\input");//Step 1: Reading the file     
    
    Scanner sc=new Scanner(file);										//Step 2: Reading the first line
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Product> goodiesProduct = new ArrayList<Product>();

    while(sc.hasNextLine())  	//To get Every single line in text file
    {
      String current[] = sc.nextLine().split(": ");
      goodiesProduct.add(new Product(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodiesProduct, new Comparator<Product>()
    {
      public int compare(Product a, Product b)
      { 
        return a.price - b.price; 
      } 
    });

    int min_diff = goodiesProduct.get(goodiesProduct.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodiesProduct.size()-number_of_employees+1;i++)
    {
      int diff = goodiesProduct.get(number_of_employees+i-1).price-goodiesProduct.get(i).price;

      if(diff<=min_diff)
      {
        min_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter writer = new FileWriter("C:\\Users\\sushanth\\workspacejava\\Assignment"
    		+ "\\src\\com\\highpeak\\goodies\\pkg1\\output"); 		// May throw an i/o exception
    writer.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++)
    {
    	writer.write(goodiesProduct.get(i).toString() + "\n");
    }

    writer.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
    writer.close();
  }
}