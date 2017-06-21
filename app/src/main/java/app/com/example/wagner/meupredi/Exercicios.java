package app.com.example.wagner.meupredi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Exercicios extends Fragment {

    //CarouselPicker carouselMeta;
    TextView chamadaDesempenho;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View view = inflater.inflate(R.layout.fragment_exercicios, container, false);

  //      carouselMeta = (CarouselPicker) view.findViewById(R.id.carousel_meta);
        chamadaDesempenho = (TextView) view.findViewById(R.id.text_veja_seu_desempenho);

/*        List<CarouselPicker.PickerItem> textItems = new ArrayList<>();
        textItems.add(new CarouselPicker.TextItem("Meta Diária", 8)); // 8 é o tamanho da fonte
        textItems.add(new CarouselPicker.TextItem("Meta Semanal", 8));
        textItems.add(new CarouselPicker.TextItem("Meta Mensal", 8));

        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(view.getContext(), textItems, 0);
        carouselMeta.setAdapter(textAdapter);
*/
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercicios");
    }
}