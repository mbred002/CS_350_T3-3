//include fstream, string, and iomanip libraries
#include <iostream>
#include <fstream>
#include <string>
#include <iomanip>

using namespace std;
//create struct item with all of the following constraints given
struct Item
{
    string name;
    string type;
    double price;
    int calories;
    int sales;
    int id;
};
//create struct combo with all of the following given constraints
struct Combo
{
    Item items [3];
    double price;
    int calories;
    int sales;
    int size;
    int citLn = 3;

    };
//include all of the prototypes of the functions told to make
void initItems (Item items[], int inputLt);
void initCombos (Combo combos[], int combosLt);
void loadItems (Item items[], int itemsLt, ifstream& inFile);
void actionMenu (Item items[], Combo combos[], int itemsLt, int combosLt);
void insertCombo (Combo combos[], int combosLt, Item items[]);


int main()
{
    //create variable to input
    ifstream inFile;
    //open file
    inFile.open("items.txt");
    //create length variables for items and combos arrays
    int itemsLt;
    int combosLt = 9;
    //get number of items
    inFile >> itemsLt;
    //create items and combos arrays
    Item items [itemsLt];
    Combo combos [combosLt];

    //run all of the functions
    initItems (items, itemsLt);
    initCombos(combos, combosLt);
    loadItems (items, itemsLt, inFile);
    actionMenu(items, combos, itemsLt, combosLt);
    //close the input file
    inFile.close();

    return 0;
}



//in the function void loadItems use items array, int itemsLt, and ifstream& inFile as parameters
//run a loop through all of the indexes of items and read the file and input accordingly
void loadItems (Item items[], int itemsLt, ifstream& inFile)
{
    for (int i = 0; i<itemsLt; i++)
    {
        inFile >> items[i].name >> items[i].type >> items[i].price >> items[i].calories >> items[i].sales;
        items[i].id = i;
    }
}
//in the function void actionMenu use array items, array combos, int itemsLt, and int combosLt as parameters
//create a continue variable then give the user the option of what they want to do.
//Determine the entered number then either print the menu, allow the cutomer to create a combo or output an error
//and allow the user to determine whether they would like to print again or make another combo
void actionMenu (Item items[], Combo combos[], int itemsLt, int combosLt)
{
    int cont;
    cout << "1.  Print Menu" << endl << "2.  Create Combo" << endl << "0.  Exit" << endl;
    cin >> cont;
    cout << endl << endl;

     do
    {


        if (cont != 1 && cont != 2)
        {
            while (cont != 1 && cont != 2)
                   {
            cout << "Please enter number corresponding with selection" << endl;
            cout << "1.  Print Menu" << endl << "2.  Create Combo" << endl << "0.  Exit" << endl;
            cin >> cont;
                }
        }
        else if (cont == 1)
        {
            cout <<fixed << right << "ID" << setw(5) << "NAME" << setw(21) << "TYPE" << setw(15) << "CALORIES" << setw(11)
                     << "SALES" << setw(12) << "PRICE" << endl;

            for (int i = 0; i< itemsLt; i++)
            {
                cout << showpoint << fixed << setprecision(2);
                cout << fixed << left << setw(4) << items[i].id << setw(20) << items[i].name  << setw(11) << items[i].type << setw(14) << items[i].calories << setw(12)
                     << items[i].sales << setw(7) << items[i].price << endl;
            }
            cout << endl << endl << "NO COMBOS AVAILABLE" << endl << endl << endl;
        }
        else
            insertCombo(combos, combosLt, items);

        cout << endl << endl << "Is there anything else we could add to your order today?" << endl
             << "1.  Print Menu" << endl << "2.  Create Combo" << endl << "0.  Exit" << endl;

        cin >> cont;
    }
    while (cont != 0);
}
//in this function void initItems it uses array items and int itemLt
//initialize all of the variables to 0 or strings to ""
void initItems (Item items [], int itemsLt)
    {
    for(int i = 0; i < itemsLt; i++)
        {
        items[i].name = "";
        items[i].type = "";
        items[i].price = 0.0;
        items[i].calories = 0;
        items[i].sales = 0;
        items[i].id = 0;
        };
    }
//in void function initCombos use combos array and int combosLt as parameters
//initialize all of the variables inside of the combos struct as 0 or "" if string
void initCombos (Combo combos[] , int combosLt)
    {


    for (int i = 0; i<combosLt; i++)
        {
        for (int j = 0; j<combos[i].citLn; j++)
            {
            combos[i].items[j].name = "";
            combos[i].items[j].type = "";
            combos[i].items[j].price = 0.0;
            combos[i].items[j].sales = 0;


            }
        combos[i].price = 0.0;
        combos[i].sales = 0;
        combos[i].calories = 0;



        };
    }

//in the void function insertCombo use combos array, items array, and combosLt as parameters
// ask the customer what three items they want to turn into a combo, print those items and give a total price
//with a 25% discount
void insertCombo (Combo combos[], int combosLt, Item items[])
{
    int j = 0;
    int i = 0;
    int cont = 1;
    while(i< combosLt && cont == 1)
        {
        cout << "Please enter a 3 food IDs the item you want added to your combo: " << endl;
        while (j < combos->citLn)
        {
        cin >> combos[i].items[j].id;
        combos[i].items[j].name = items[combos[i].items[j].id].name;
        combos[i].items[j].price = items[combos[i].items[j].id].price;
        combos[i].items[j].calories = items[combos[i].items[j].id].calories;
        combos[i].items[j].sales = items[combos[i].items[j].id].sales;
        cout << combos[i].items[j].name << " has been added to your combo" << endl;

        j++;
        }

        combos[i].price = (combos[i].items[0].price + combos[i].items[1].price + combos[i].items[2].price) - (.25 *(combos[i].items[0].price + combos[i].items[1].price + combos[i].items[2].price));
        combos[i].sales = 0;
        combos[i].calories = combos[i].items[0].calories + combos[i].items[1].calories + combos[i].items[2].calories;

        cout << endl << "Your combo consists of: " << endl << combos[i].items[0].name << endl << combos[i].items[1].name
             << endl << combos[i].items[2].name << endl << endl << "Total Calories: " << combos[i].calories;
        cout << fixed << showpoint << setprecision(2) << endl << "Your Total: $" << combos[i].price;

        cout << endl << endl << "would you like to make another combo? (1 = yes, 0 = no) " << endl;
        cin >> cont;
        if (cont == 1)
        {
            i++;
            j=0;
        }

        }
}
