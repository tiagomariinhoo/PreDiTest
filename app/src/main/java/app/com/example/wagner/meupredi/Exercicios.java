package app.com.example.wagner.meupredi;

import android.content.Intent;
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

    private TextView chamadaDesempenho;
    private TextView chamadaGinasio;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercicios, container, false);

        chamadaGinasio = (TextView) view.findViewById(R.id.text_chamada_ginasio);
        chamadaGinasio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaGinasio = new Intent(getActivity(), Ginasio.class);
                startActivity(telaGinasio);
            }
        });

        chamadaDesempenho = (TextView) view.findViewById(R.id.text_veja_seu_desempenho);
        chamadaDesempenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent desempenho = new Intent(getActivity(), Desempenho.class);
                startActivity(desempenho);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Exercicios");
    }
}