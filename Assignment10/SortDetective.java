// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortDetective {
   private int accessCount = 0;

   public SortDetective() {
   }

   private static int linearSearch(int var0, int[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var1[var2] == var0) {
            return var2;
         }
      }

      return -1;
   }

   private static int binarySearchHelper(int var0, int[] var1, int var2, int var3) {
      if (var3 <= var2) {
         return -1;
      } else {
         int var4 = (var2 + var3) / 2;
         if (var1[var4] > var0) {
            return binarySearchHelper(var0, var1, var2, var4);
         } else {
            return var1[var4] < var0 ? binarySearchHelper(var0, var1, var4 + 1, var3) : var4;
         }
      }
   }

   private static int binarySearch(int var0, int[] var1) {
      return binarySearchHelper(var0, var1, 0, var1.length);
   }

   private static void selectionSort(int[] var0) {
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1 - 1; ++var2) {
         int var3 = var2;

         int var4;
         for(var4 = var2 + 1; var4 < var1; ++var4) {
            if (var0[var4] < var0[var3]) {
               var3 = var4;
            }
         }

         var4 = var0[var3];
         var0[var3] = var0[var2];
         var0[var2] = var4;
      }

   }

   private static void insertionSort(int[] var0) {
      int var1 = var0.length;

      for(int var2 = 1; var2 < var1; ++var2) {
         int var3 = var0[var2];

         int var4;
         for(var4 = var2 - 1; var4 >= 0 && var0[var4] > var3; --var4) {
            var0[var4 + 1] = var0[var4];
         }

         var0[var4 + 1] = var3;
      }

   }

   private static void merge(int[] var0, int[] var1, int var2, int var3, int var4) {
      int var5 = var2;
      int var6 = var3;
      int var7 = var4 - var2;

      int var8;
      for(var8 = 0; var8 < var7; ++var8) {
         if (var5 == var3) {
            var0[var8] = var1[var6++];
         } else if (var6 == var4) {
            var0[var8] = var1[var5++];
         } else if (var1[var6] < var1[var5]) {
            var0[var8] = var1[var6++];
         } else {
            var0[var8] = var1[var5++];
         }
      }

      for(var8 = 0; var8 < var7; ++var8) {
         var1[var2 + var8] = var0[var8];
      }

   }

   private static void mergeSort(int[] var0, int[] var1, int var2, int var3) {
      int var4 = var3 - var2;
      if (var4 > 1) {
         int var5 = var2 + var4 / 2;
         mergeSort(var0, var1, var2, var5);
         mergeSort(var0, var1, var5, var3);
         merge(var0, var1, var2, var5, var3);
      }
   }

   private static void printArr(int[] var0) {
      int[] var1 = var0;
      int var2 = var0.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         int var4 = var1[var3];
         System.out.print(var4 + "\t");
      }

      System.out.println();
   }

   private static boolean checkSorted(int[] var0) {
      for(int var1 = 1; var1 < var0.length - 1; ++var1) {
         if (var0[var1] > var0[var1 + 1]) {
            return false;
         }
      }

      return true;
   }

   private static int[] toIntArr(List<Integer> var0) {
      int[] var1 = new int[var0.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = (Integer)var0.get(var2);
      }

      return var1;
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      ArrayList var2 = new ArrayList();
      System.out.println("**********************************************");
      System.out.println("Sort Detective works on integer arrays.\n");
      System.out.println("Choose a mode to input the array:");
      System.out.println("\t1: read from file\n\t2: read from terminal\n\t3: generate a random array\n\t4: exit the program");
      System.out.println("**********************************************");
      System.out.print("Select input mode:");
      boolean var3 = true;
      boolean var4 = true;
      int var22 = var1.nextInt();
      var1.nextLine();
      if (var22 == 1) {
         System.out.print("enter file path:");
         String var5 = var1.nextLine();

         try {
            Scanner var6 = new Scanner(new File(var5));

            while(var6.hasNext()) {
               var2.add(var6.nextInt());
            }

            var6.close();
         } catch (FileNotFoundException var21) {
            System.out.println("Invalid file path");
            System.exit(0);
         }
      } else {
         int var24;
         int var26;
         if (var22 == 2) {
            System.out.print("enter array size:");
            var24 = var1.nextInt();

            for(var26 = 0; var26 < var24; ++var26) {
               var2.add(var1.nextInt());
            }
         } else {
            if (var22 != 3) {
               if (var22 == 4) {
                  System.out.println("goodbye");
                  return;
               }

               System.out.println("Invalid input, goodbye");
               return;
            }

            System.out.print("enter array size:");
            var24 = var1.nextInt();

            for(var26 = 0; var26 < var24; ++var26) {
               var2.add((int)(Math.random() * 1000.0 - 500.0));
            }
         }
      }

      int[] var25 = toIntArr(var2);
      boolean var27 = true;
      String[] var7 = new String[]{"sorting 1", "sorting 2", "sorting 3"};
      String var8 = "";
      String var9 = String.format("Note: an integer takes 4 bytes of space. Space consumption includes the array size and the local variables used in the method.");
      long var10 = 0L;
      long var12 = 0L;

      while(true) {
         while(var27) {
            System.out.println("\n\n**********************************************");
            System.out.println("1: print array:");
            System.out.println("2: apply sorting algorithm");
            System.out.println("3: apply searching algorithm");
            System.out.println("4: re-enter an array");
            System.out.println("5: rename an algorithm");
            System.out.println("6: exit program");
            System.out.println("**********************************************");
            System.out.print("select your action: ");
            var22 = var1.nextInt();
            int var17;
            int var18;
            int var19;
            int var23;
            int var29;
            switch (var22) {
               case 1:
                  printArr(var25);
                  break;
               case 2:
                  System.out.println("1: " + var7[0] + "  2: " + var7[1] + "  3: " + var7[2] + "  4: try all sorting algorithms");
                  System.out.print("select which sorting algorithm to use: ");
                  var23 = var1.nextInt();
                  switch (var23) {
                     case 1:
                        int[] var28 = new int[var25.length];
                        var10 = System.nanoTime();
                        mergeSort(var28, var25, 0, var25.length);
                        var12 = System.nanoTime();

                        for(var29 = 0; var29 < var25.length; ++var29) {
                           var25[var29] = var28[var29];
                        }

                        System.out.println("Space: " + (28 + var25.length * 8) + " bytes");
                        System.out.println("Time : " + (var12 - var10) + " nanoseconds\n");
                        System.out.println(var9);
                        continue;
                     case 2:
                        var10 = System.nanoTime();
                        insertionSort(var25);
                        var12 = System.nanoTime();
                        System.out.println("Space: " + (16 + var25.length * 4) + " bytes");
                        System.out.println("Time : " + (var12 - var10) + " nanoseconds\n");
                        System.out.println(var9);
                        continue;
                     case 3:
                        var10 = System.nanoTime();
                        selectionSort(var25);
                        var12 = System.nanoTime();
                        System.out.println("Space: " + (16 + var25.length * 4) + " bytes");
                        System.out.println("Time : " + (var12 - var10) + " nanoseconds\n");
                        System.out.println(var9);
                        continue;
                     case 4:
                        int[] var30 = new int[var25.length];
                        int[] var31 = new int[var25.length];

                        for(var17 = 0; var17 < var25.length; ++var17) {
                           var31[var17] = var25[var17];
                        }

                        int[] var33 = new int[var25.length];

                        for(var18 = 0; var18 < var25.length; ++var18) {
                           var33[var18] = var25[var18];
                        }

                        int[] var34 = new int[var25.length];

                        for(var19 = 0; var19 < var25.length; ++var19) {
                           var34[var19] = var25[var19];
                        }

                        var10 = System.nanoTime();
                        mergeSort(var30, var31, 0, var31.length);
                        var12 = System.nanoTime();
                        System.out.println("Algorithm 1");
                        System.out.println("\tSpace: " + (28 + var25.length * 8) + " bytes");
                        System.out.println("\tTime : " + (var12 - var10) + " nanoseconds\n");
                        var10 = System.nanoTime();
                        insertionSort(var33);
                        var12 = System.nanoTime();
                        System.out.println("Algorithm 2:");
                        System.out.println("\tSpace: " + (16 + var25.length * 4) + " bytes");
                        System.out.println("\tTime : " + (var12 - var10) + " nanoseconds\n");
                        var10 = System.nanoTime();
                        selectionSort(var34);
                        var12 = System.nanoTime();
                        System.out.println("Algorithm 3:");
                        System.out.println("\tSpace: " + (16 + var25.length * 4) + " bytes");
                        System.out.println("\tTime : " + (var12 - var10) + " nanoseconds\n");
                        System.out.println(var9);
                        var25 = var34;
                        continue;
                     default:
                        System.out.println("invalid input");
                        continue;
                  }
               case 3:
                  System.out.print("enter searching targert:");
                  int var14 = var1.nextInt();
                  System.out.println("1: linear search  2: binary search");
                  System.out.print("select which searching algorithm to use: ");
                  var23 = var1.nextInt();
                  boolean var15 = true;
                  switch (var23) {
                     case 1:
                        var29 = linearSearch(var14, var25);
                        System.out.println("target found at index: " + var29);
                        continue;
                     case 2:
                        if (!checkSorted(var25)) {
                           System.out.println("array not sorted");
                        } else {
                           var29 = binarySearch(var14, var25);
                           System.out.println("target found at index: " + var29);
                        }
                        continue;
                     default:
                        System.out.println("invalid input");
                        continue;
                  }
               case 4:
                  var2 = new ArrayList();
                  System.out.println("1: read from file;\n2: read from terminal;\n3: generate a random array");
                  System.out.print("Select input mode:");
                  int var16 = var1.nextInt();
                  var1.nextLine();
                  label111:
                  switch (var16) {
                     case 1:
                        System.out.print("enter file path:");
                        String var32 = var1.nextLine();

                        try {
                           Scanner var35 = new Scanner(new File(var32));

                           while(var35.hasNext()) {
                              var2.add(var35.nextInt());
                           }

                           var35.close();
                        } catch (FileNotFoundException var20) {
                           System.out.println("Invalid file path");
                           System.exit(0);
                        }
                        break;
                     case 2:
                        System.out.print("enter array size:");
                        var17 = var1.nextInt();
                        var19 = 0;

                        while(true) {
                           if (var19 >= var17) {
                              break label111;
                           }

                           var2.add(var1.nextInt());
                           ++var19;
                        }
                     case 3:
                        System.out.print("enter array size:");
                        var17 = var1.nextInt();
                        var19 = 0;

                        while(true) {
                           if (var19 >= var17) {
                              break label111;
                           }

                           var2.add((int)(Math.random() * 1000.0 - 500.0));
                           ++var19;
                        }
                     default:
                        System.out.println("Invalid input");
                        continue;
                  }

                  var25 = toIntArr(var2);
                  break;
               case 5:
                  System.out.println("1: " + var7[0] + "  2: " + var7[1] + "  3: " + var7[2]);
                  System.out.print("select the method you want to rename: ");
                  var18 = var1.nextInt();
                  var1.nextLine();
                  System.out.println("enter the new name: ");
                  if (0 < var18 && var18 < 4) {
                     var7[var18 - 1] = var1.nextLine();
                     break;
                  }

                  System.out.print("invalid input");
                  break;
               case 6:
                  System.exit(0);
                  break;
               default:
                  System.out.println("invalid input");
            }
         }

         var1.close();
         return;
      }
   }
}
