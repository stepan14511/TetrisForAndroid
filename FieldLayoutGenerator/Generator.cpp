#include <iostream>
#include <fstream>

using namespace std;

int main(){
    cout << "Arrows: ";
    int arrows = 0;
    cin >> arrows;
    cout << "Lines: ";
    int lines = 0;
    cin >> lines;
    cout << "Size(one number)(in dp's): ";
    int size_of_block;
    cin >> size_of_block;
    ofstream output_file_java;
    ofstream output_file_xml;
    output_file_java.open("OUTPUT_JAVA.txt");
    output_file_xml.open("OUTPUT_XML.txt");
    output_file_java << "{\n";
    for(int i = 0; i < lines; i++){
        for(int j = 0; j < arrows; j++){
            //xml generation
            output_file_xml<<"<ImageView android:id=\"@+id/fld_"<<i<<"_"<<j<<"\" android:layout_width=\""<<size_of_block<<"dp\" android:layout_height=\""<<size_of_block<<"dp\" android:background=\"#FFFFFF\"";
            if(i != 0)
                output_file_xml<<" android:layout_below=\"@id/fld_"<<i-1<<"_"<<j<<"\" android:layout_marginTop=\"2dp\"";
            if(j != 0)
                output_file_xml<<" android:layout_toRightOf=\"@id/fld_"<<i<<"_"<<j-1<<"\" android:layout_marginLeft=\"2dp\"";
            output_file_xml << "/>\n";

            //java generation
            if(j == 0)
                output_file_java << "{";
            output_file_java << "R.id.fld_" << i << "_" << j;
            if(j == arrows - 1){
                if(i == lines - 1)
                    output_file_java << "}};";
                else
                    output_file_java << "},";
            }
            else
                output_file_java << ", ";
        }
        output_file_xml << "\n";
        output_file_java << "\n";
    }
    output_file_xml.close();
    output_file_java.close();
    return 0;
}
