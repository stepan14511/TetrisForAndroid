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
    ofstream output_file;
    output_file.open("OUTPUT_CODE.txt");
    for(int i = 0; i < lines; i++){
        for(int j = 0; j < arrows; j++){
            output_file<<"<ImageView android:id=\"@+id/fld_"<<i<<"_"<<j<<"\" android:layout_width=\""<<size_of_block<<"dp\" android:layout_height=\""<<size_of_block<<"dp\" android:background=\"#FFFFFF\"";
            if(i != 0)
                output_file<<" android:layout_below=\"@id/fld_"<<i-1<<"_"<<j<<"\" android:layout_marginTop=\"2dp\"";
            if(j != 0)
                output_file<<" android:layout_toRightOf=\"@id/fld_"<<i<<"_"<<j-1<<"\" android:layout_marginLeft=\"2dp\"";
            output_file << "/>\n";
        }
        output_file << "\n";
    }
    output_file.close();
    return 0;
}
