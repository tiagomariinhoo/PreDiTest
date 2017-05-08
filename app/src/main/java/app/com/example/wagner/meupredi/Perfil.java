package app.com.example.wagner.meupredi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LeandroDias1 on 18/04/2017.
 */

public class Perfil extends Fragment {

    MenuPrincipal menu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        Log.d("Começando ", "O 231231");
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ((MenuPrincipal)getActivity()).pegarPacienteMenu();

        Log.d("Começando ", "O CALCULOO123123");
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Perfil");
    }

}
