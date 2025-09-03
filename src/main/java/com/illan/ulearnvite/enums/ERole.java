package com.illan.ulearnvite.enums;

public enum ERole {

    ROLE_USER("Utilisateur standard"),
    ROLE_APPRENANT("Apprenant"),
    ROLE_FORMATEUR("Formateur"),
    ROLE_ADMIN("Administrateur");

    private final String description;

    ERole(String description) {
        this.description = description;
    }

    // Retourner le nom du rôle de manière plus lisible
    @Override
    public String toString() {
        return description;
    }

    // Vous pouvez aussi garder la méthode pour obtenir la description si nécessaire
    public String getDescription() {
        return description;
    }
}
