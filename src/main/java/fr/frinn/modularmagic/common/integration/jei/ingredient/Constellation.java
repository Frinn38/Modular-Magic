package fr.frinn.modularmagic.common.integration.jei.ingredient;

import hellfirepvp.astralsorcery.common.constellation.IConstellation;

public class Constellation {

    private IConstellation constellation;

    public Constellation(IConstellation constellation) {
        this.constellation = constellation;
    }

    public IConstellation getConstellation() {
        return constellation;
    }
}
