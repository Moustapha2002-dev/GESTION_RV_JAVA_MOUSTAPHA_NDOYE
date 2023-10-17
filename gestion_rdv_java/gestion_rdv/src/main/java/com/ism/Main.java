package com.ism;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.ism.entities.Medecin;
import com.ism.entities.Patient;
import com.ism.entities.Rdv;
import com.ism.repositories.DatabaseImpl;
import com.ism.repositories.MedecinImpl;
import com.ism.repositories.PatientImpl;
import com.ism.repositories.RdvImpl;
import com.ism.services.PersonneServiceImpl;
import com.ism.services.RdvServiceImpl;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int choix;
        DatabaseImpl database = new DatabaseImpl();
        PatientImpl patientImpl= new PatientImpl(database);
        MedecinImpl medecinImpl= new MedecinImpl(database);
        RdvImpl rdvImpl = new RdvImpl(database);
        PersonneServiceImpl<Patient> patientServiceImpl = new PersonneServiceImpl<>(patientImpl);
        PersonneServiceImpl<Medecin> medecinServiceImpl = new PersonneServiceImpl<>(medecinImpl);
        RdvServiceImpl<Rdv> rdvServiceImpl= new RdvServiceImpl<>(rdvImpl);
        do {
            System.out.println("1. Créer un patient");
            System.out.println("2. Créer un médecin");
            System.out.println("3. Planifier un rendez-vous");
            System.out.println("4. Afficher les rendez-vous du jour");
            System.out.println("5. Afficher les rendez-vous d'un médecin par jour");
            System.out.println("6. Annuler un rendez-vous");
            System.out.println("7. Quitter");
            choix = sc.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("Entrer le nom complet du patient : ");
                    String nomCompletP = sc.next();
                    Patient patient = new Patient();
                    patient.setNomComplet(nomCompletP);
                    patient.setType(1); 
                    System.out.println("Entrer les antécédents du patient : ");
                    String antecedents = sc.next();
                    patient.setAntecedents(antecedents);

                    int resultP = patientServiceImpl.add(patient);

                    if (resultP > 0) {
                        System.out.println("Patient inséré avec succès !");
                    } else {
                        System.out.println("Échec de l'insertion du patient.");
                    }
                    break;


                case 2:
                    System.out.println("Entrer le nom complet du médecin : ");
                    String nomCompletM = sc.next();
                    Medecin medecin = new Medecin();
                    medecin.setNomComplet(nomCompletM);
                    medecin.setType(2);
                    System.out.println("Entrer la spécialité du médecin : ");
                    String specialite = sc.next();
                    medecin.setSpecialite(specialite);

                    int resultM = medecinServiceImpl.add(medecin);

                    if (resultM > 0) {
                        System.out.println("Médecin inséré avec succès !");
                    } else {
                        System.out.println("Échec de l'insertion du médecin.");
                    }
                    break;
                case 3:
                Rdv rdv = new Rdv();
                    System.out.println("Entrer la date du rendez-vous (format yyyy-MM-dd) : ");
                    String dateRdv = sc.next();
                    System.out.println("Entrer le nom complet du médecin : ");
                    String nomCompletMedecin = sc.next().toLowerCase(); 
                    System.out.println("Entrer le nom complet du patient : ");
                    String nomCompletPatient = sc.next().toLowerCase();
                    String medecin2 = medecinImpl.findMedecin(nomCompletMedecin);
                    if (medecin2 == null) {
                        medecin = new Medecin();
                        medecin.setNomComplet(nomCompletMedecin);
                        medecin.setType(2); 
                        System.out.println("Entrer la spécialité du médecin : ");
                        String specialite2 = sc.next();
                        medecin.setSpecialite(specialite2);

                        int resultM2 = medecinServiceImpl.add(medecin);
                        if (resultM2 > 0) {
                            System.out.println("Médecin créé avec succès !");
                            medecin.setId(resultM2); // Récupérer l'ID du médecin créé
                        } else {
                            System.out.println("Échec de la création du médecin.");
                            break;
                        }
                    }

                    // Vérifier si le patient existe, sinon, le créer
                    String patient2 = patientImpl.findPatient(nomCompletPatient);
                    if (patient2 == null) {
                        patient = new Patient();
                        patient.setNomComplet(nomCompletPatient);
                        patient.setType(1); // Type par défaut pour un patient
                        System.out.println("Entrer les antécédents du patient : ");
                        String antecedents2 = sc.next();
                        patient.setAntecedents(antecedents2);

                        int resultP2 = patientServiceImpl.add(patient);
                        if (resultP2 > 0) {
                            System.out.println("Patient créé avec succès !");
                            patient.setId(resultP2); // Récupérer l'ID du patient créé
                        } else {
                            System.out.println("Échec de la création du patient.");
                            break;
                        }
                    }
                    rdv.setDate(dateRdv);
                    rdv.setMedecin(medecin2);
                    rdv.setPatient(patient2);
                    rdv.setStatut(1);
                    int resultR= rdvServiceImpl.add(rdv);

                    if (resultR > 0) {
                        System.out.println("Rendez-vous créé avec succès !");
                    } else {
                        System.out.println("Échec de la création du rendez-vous.");
                    }
                    break;
                case 4:
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateDuJour = new Date();
                    String dateActuelle = dateFormat.format(dateDuJour);

                    // Rechercher les rendez-vous pour la date actuelle
                    ArrayList<Rdv> rdvDuJour = rdvImpl.findRdvByDate(dateActuelle);

                    if (!rdvDuJour.isEmpty()) {
                        System.out.println("Rendez-vous prévus pour aujourd'hui :");
                        for (Rdv rdv2 : rdvDuJour) {
                            String statut = rdv2.getStatut() == 1 ? "confirmé" : "annulé";
                            System.out.println("ID: " + rdv2.getId() + " - Médecin: " + rdv2.getMedecin() + " - Patient: " + rdv2.getPatient() + " - Date: " + rdv2.getDate() + " - Statut: " + statut);
                        }
                    } else {
                        System.out.println("Aucun rendez-vous prévu pour aujourd'hui.");
                    }
                    break;

                case 5:
                    break;
                case 6:
                    // Afficher tous les rendez-vous pour que l'utilisateur puisse choisir celui à annuler
                    ArrayList<Rdv> tousLesRdv = rdvImpl.findAll();
                
                    if (tousLesRdv.isEmpty()) {
                        System.out.println("Aucun rendez-vous trouvé.");
                    } else {
                        System.out.println("Liste des rendez-vous :");
                        for (Rdv rdv3 : tousLesRdv) {
                            System.out.println("ID: " + rdv3.getId() + " - Médecin: " + rdv3.getMedecin() + " - Patient: " + rdv3.getPatient() + " - Date: " + rdv3.getDate() + " - Statut: " + "confirmé");
                        }
                
                        System.out.println("Entrer l'ID du rendez-vous à annuler : ");
                        int idRdvAAnnuler = sc.nextInt();
                
                        // Rechercher le rendez-vous par ID
                        Rdv rdvAAnnuler = null;
                        for (Rdv rdv3 : tousLesRdv) {
                            if (rdv3.getId() == idRdvAAnnuler) {
                                rdvAAnnuler = rdv3;
                                break;
                            }
                        }
                
                        if (rdvAAnnuler != null) {
                            // Demander confirmation à l'utilisateur
                            System.out.println("Confirmez-vous l'annulation de ce rendez-vous ? (Oui/Non)");
                            String confirmation = sc.next();
                
                            if (confirmation.equalsIgnoreCase("Oui")) {
                                // Mettre à jour le statut du rendez-vous à "annulé" (0)
                                rdvAAnnuler.setStatut(0);
                                int result = rdvImpl.updateStatut(rdvAAnnuler);
                
                                if (result > 0) {
                                    System.out.println("Rendez-vous annulé avec succès.");
                                } else {
                                    System.out.println("Échec de l'annulation du rendez-vous.");
                                }
                            } else {
                                System.out.println("Annulation du rendez-vous annulée.");
                            }
                        } else {
                            System.out.println("Rendez-vous non trouvé avec l'ID spécifié.");
                        }
                    }
                    break;
                
                case 7:
                    break;
                default:
                    break;
            }
        } while (choix != 7);
        sc.close();
    }
}
