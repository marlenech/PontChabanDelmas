package com.italikdesign.pont.chaban;

/**
 * Created by italikdesign on 25/05/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AproposFragment extends Fragment {

    private View rootView;


    public AproposFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_apropos, container, false);
        // Inflate the layout for this fragment

        TextView feedback = (TextView) rootView.findViewById(R.id.contact);
        feedback.setText(Html.fromHtml("<a href=\"mailto:italikdesignbordeaux@gmail.com\">italikdesignbordeaux@gmail.com</a>"));
        feedback.setMovementMethod(LinkMovementMethod.getInstance());

        TextView feedback1 = (TextView) rootView.findViewById(R.id.lien_site1);
        feedback1.setText(Html.fromHtml("<a href=\"http://sedeplacer.bordeaux-metropole.fr/Toutes-les-infos-circulation/Pont-Chaban-Delmas-Fermetures\">http://sedeplacer.bordeaux-metropole.fr</a>"));
        feedback1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView feedback2 = (TextView) rootView.findViewById(R.id.lien_opendata);
        feedback2.setText(Html.fromHtml("<a href=\"http://data.bordeaux-metropole.fr/data.php?layer=PREVISIONS_PONT_CHABAN\">http://data.bordeaux-metropole.fr</a>"));
        feedback2.setMovementMethod(LinkMovementMethod.getInstance());



        return rootView;

    }


}
