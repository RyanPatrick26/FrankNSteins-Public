package com.example.install.franknsteins;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Create the email variables
    private String emailAddress = "franknsteins@gmail.com";
    private String emailSubject = "Question regarding Frank \'N\' Steins";
    private String emailBody = "I have a question regarding your restaurant...\n";

    // Create the add contact variables
    private String contactName = "Frank \'N\' Steins";
    private String contactNumber = "5197803445";

    // Create the buttons
    Button emailButton;
    Button addContactButton;
    Button findLocationButton;
    Button websiteButton;
    Button shareButton;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        // Instantiate the buttons
        emailButton = (Button) view.findViewById(R.id.emailButton);
        addContactButton = (Button) view.findViewById(R.id.addContactButton);
        findLocationButton = (Button) view.findViewById(R.id.findLocationButton);
        websiteButton = (Button) view.findViewById(R.id.websiteButton);
        shareButton = (Button) view.findViewById(R.id.shareButton);
        // Add OnClick listeners to the buttons
        emailButton.setOnClickListener(sendEmail);
        addContactButton.setOnClickListener(addToContacts);
        findLocationButton.setOnClickListener(findLocation);
        websiteButton.setOnClickListener(goToWebsite);
        shareButton.setOnClickListener(shareWithFriend);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * sendEmail
     * This OnClickListener will attempt to open up the user's email client
     * and populate a basic template for an email to send to the Frank 'N'
     * Steins email address.
     * If the user does not have an email client installed on their device,
     * a Snackbar informing the user of this will be displayed instead.
     *
     * @author Nicholas Allaire
     */
    public View.OnClickListener sendEmail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // populate a string array with the email address
            String[] addressEmail = {emailAddress};
            // create a new sendto action intent
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            // set the URI data to 'mailto:'
            intent.setData(Uri.parse("mailto:"));
            // add the recipient email address
            intent.putExtra(Intent.EXTRA_EMAIL, addressEmail);
            // add the subject line to the email
            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
            // add the intro body of the email
            intent.putExtra(Intent.EXTRA_TEXT, emailBody);
            // check if the user has an email client installed
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                // launch the intent
                startActivity(intent);
            } else {
                // if they don't have an email client, display a snackbar
                if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        }
    };

    /**
     * addToContacts
     * This OnClickListener will attempt to open up the user's contacts
     * and add the restaurant to the user's contact list.
     * If the user does not have the correct software installed on their device,
     * a Snackbar informing the user of this will be displayed instead.
     *
     * @author Nicholas Allaire
     */
    public  View.OnClickListener addToContacts = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // create an action insert intent
            Intent intent = new Intent(Intent.ACTION_INSERT);
            // set the type of the intent
            intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
            // set the name of the contact
            intent.putExtra(ContactsContract.Intents.Insert.NAME, contactName);
            // set the phone number of the contact
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress);
            // set the phone number of the contact
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, contactNumber);
            // check to see if the user can add contacts
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // if they don't have the ability to add contacts on their device, display a snackbar
                if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        }
    };

    /**
     * findLocation
     * This OnClickListener will attempt to open up the user's maps software
     * and populate it with the location of the restaurant.
     * If the user does not have the correct software installed on their device,
     * a Snackbar informing the user of this will be displayed instead.
     *
     * @author Nicholas Allaire
     */
    public View.OnClickListener findLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create a uri for the location of the restaurant
            Uri restaurantLocation = Uri.parse("geo:0,0?q=42.2979139,-83.0441145(Frank 'N' Steins)");
            // Create the action view intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(restaurantLocation);
            // Check to see if the user has a map client on their device
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // if they don't have a maps software on their device, display a snackbar
                if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        }
    };

    /**
     * goToWebsite
     * This OnClickListener will attempt to open up the user's web browser and
     * navigate to the Frank 'N' Steins website.
     * If the user does not have the correct software installed on their device,
     * a Snackbar informing the user of this will be displayed instead.
     *
     * @author Nicholas Allaire
     */
    public View.OnClickListener goToWebsite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // create the uri for the website
            Uri website = Uri.parse("https://www.franknsteins.ca");
            // create the action view intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            // set the data for the intent
            intent.setData(website);
            // check to see if the user has a web browser that they're able to use
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        }
    };

    /**
     * shareWithFriend
     * This OnClickListener will attempt to open up the user's SMS application
     * and send a pre-written message to a number of their choosing.
     * If the user does not have the correct software installed on their device,
     * a Snackbar informing the user of this will be displayed instead.
     *
     * @author Nicholas Allaire
     */
    public View.OnClickListener shareWithFriend = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // create the action send to intent
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            // set the 'smsto:' data for the intent
            intent.setData(Uri.parse("smsto:"));
            // set the body of the sms
            intent.putExtra("sms_body", "Check out this amazing restaurant called Frank 'N' Steins! "
                        +"https://www.franknsteins.ca");
            // check to see if the user has a SMS application installed on their device
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // if the user does not have SMS, display a snackbar
                if (!Locale.getDefault().getLanguage().contentEquals("fr")) {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "No installed software to complete process.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),
                            "Aucun logiciel installé pour terminer le processus.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        }
    };
    
}
