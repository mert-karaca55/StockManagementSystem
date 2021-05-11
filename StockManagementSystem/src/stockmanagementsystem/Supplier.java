/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

/**
 *
 * @author ahmet
 */
public class Supplier {
    private int id;
    private String name;
    private String phone;
    private Product [] tedarikEdilenUrunler;
    private Store [] tedarikEdilenDepolar;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the tedarikEdilenUrunler
     */
    public Product[] getTedarikEdilenUrunler() {
        return tedarikEdilenUrunler;
    }

    /**
     * @param tedarikEdilenUrunler the tedarikEdilenUrunler to set
     */
    public void setTedarikEdilenUrunler(Product[] tedarikEdilenUrunler) {
        this.tedarikEdilenUrunler = tedarikEdilenUrunler;
    }

    /**
     * @return the tedarikEdilenDepolar
     */
    public Store[] getTedarikEdilenDepolar() {
        return tedarikEdilenDepolar;
    }

    /**
     * @param tedarikEdilenDepolar the tedarikEdilenDepolar to set
     */
    public void setTedarikEdilenDepolar(Store[] tedarikEdilenDepolar) {
        this.tedarikEdilenDepolar = tedarikEdilenDepolar;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
