/**
 *---------------------------------------------------------------------
 * Copyright Ericsson AB 2012
 *
 * All rights reserved. No part of this computer programs(s) may be
 * used, reproduced, stored in any retrieval system, or transmitted,
 * in any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise without prior written permission of
 * Ericsson AB.
 *---------------------------------------------------------------------
 */
package se.svenskaakademien.saol;

/**
 * 
 * Created 20 feb 2012
 * 
 * @author erasmat
 */
public class Word {

    private String word;
    private int homograph;
    private String wordclass;
    private String i;
    private String p;
    private String d;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getHomograph() {
        return homograph;
    }

    public void setHomograph(int homograph) {
        this.homograph = homograph;
    }

    public String getWordclass() {
        return wordclass;
    }

    public void setWordclass(String wordclass) {
        this.wordclass = wordclass;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

}
