package app.com.example.wagner.meupredi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Exames extends Fragment{

    private ImageView chamadaNovoExame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View view = inflater.inflate(R.layout.fragment_exames, container, false);

        chamadaNovoExame = (ImageView) view.findViewById(R.id.image_novo_exame);
        chamadaNovoExame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novoExame = new Intent(getActivity(), cadastroExame.class);
                startActivity(novoExame);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exames");
    }

}