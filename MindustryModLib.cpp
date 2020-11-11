#include <iostream>
#include <fstream>
#include <string>
using namespace std;

ofstream element;

struct ModMain //For configuration and create files .json and .hjson
{
	std::string Local;
	std::string LocalDel;
	
	void CreateObject(string Dir, string Name, string Prefix)
	{
		//Root of Element, create the file .json or .hjson
		
		Local=Dir + Name + Prefix;
		
		element.open(Local.c_str(), ios::app);
	}
	
	void CloseObject()
	{
		//Close file after of create.
		
		element.close();
	}
	
	void CreateRoot(string NameID, string DisplayName, string Author, string Description, double Version, double minGameVersion)
	{
		if(element.is_open())
		{
			element << "name: \"" << NameID << "\"" << endl;
			element << "displayName: \"" << DisplayName << "\"" << endl;
			element << "author: \"" << Author << "\"" << endl;
			element << "description: \"" << Description << "\"" << endl;
			element << "version: \"" << Version << "\"" << endl;
			element << "minGameVersion: \"" << minGameVersion << "\"" << endl;
		}	
	}
	
	void DeleteElement(string Directory, string Name, string Prefix)
	{
		if(!element.is_open())
		{
			LocalDel="del /q";
			LocalDel=LocalDel + Directory + Name + Prefix;
			
			system(LocalDel.c_str());
		}
	}
	
	void ResetLocal()
	{
		Local="";
		LocalDel="";
	}
};

struct ItemElement
{
	
	void ItemBasic(string ItemType, string ItemName, string ItemDescription, double ItemHardness, double ItemCost, string ItemColor6char)
	{
		/*Basic of Item: type, name in display, description and color. The color needs 6 char <color:ffffff>
		 ItemHardness and ItemCost is double <hardness: 2> <cost: 2.5>
		 For default: 0*/
		
		
		if(element.is_open())
		{
			element << "type: " << ItemType << endl;
			element << "name: " << ItemName << endl;
			element << "description: " << ItemDescription << endl;
			element << "hardness: " << ItemHardness << endl;
			element << "cost: " << ItemCost << endl;
			element << "color: " << ItemColor6char << endl;
		
		}
		
	}
	

};
