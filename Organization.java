import java.util.ArrayList;

public final class Organization 
{
    private  String name;
    private  Admin admin;
    private  ArrayList<Entity> entityList = new ArrayList<Entity>();
    private  ArrayList<Donator> donatorList = new ArrayList<Donator>();
    private  ArrayList<Beneficiary> beneficiaryList = new ArrayList<Beneficiary>();
    private  RequestDonationList currentDonations = new RequestDonationList();
    private  ArrayList<Double> EntityQuantity = new ArrayList<Double>();
    private  static Organization org;



        public Organization(String name,Admin admin) 
        {
            this.name=name;
            this.admin=admin;
            org=this;
        }

        public static Organization getOrg() //seters kai getters
        {
            return org;
        }

        public  void setName(String name)
        {
            this.name=name;
        }

        public  String getName()
        {
            return name;
        }
        
        public  void setAdmin(Admin ad)
        {
            admin=ad;
        } 

        public  Admin getAdmin()
        {
            return admin;
        }
        
        public  String getAdminame()
        {
            return admin.getName();
        }

        public  void  addEntity(Entity e) throws AlreadyExistsException 
        {
            for(var x: entityList) //elegxei an yparxei hdh
            if(e.entExists(x))
            throw new AlreadyExistsException("το αντικείμενο αυτό ήδη υπάρχει!");

            entityList.add(e);
        }

        public  void removeEntity(Entity e,User a)
        {
            if(a.getPhone().equals(admin.getPhone())) //me bash to thlefwno elegxei an o xrhsths einai o admin kai mono autos mporei na kanei remove
            {
                entityList.remove(e);
            }
            else
                System.out.println("Δεν είστε Admin!!!");      
        }

        public  void insertDonator(Donator d) throws AlreadyExistsException
        {
            for(var x: donatorList)
            if(d.donExists(x))
            throw new AlreadyExistsException("o Donator ήδη υπάρχει!");
            
            donatorList.add(d);
        }
        
        public void removeDonator(Donator d)
        {
            donatorList.remove(d);                    
        }


        public   void insertBeneficiary(Beneficiary b) throws AlreadyExistsException
        {
            for(var x: beneficiaryList)
                if(b.benefExists(x))
                throw new AlreadyExistsException("o Beneficiary ήδη υπάρχει!");
            
            beneficiaryList.add(b);
        }


        public   void removeBeneficiary(Beneficiary b)
        {
            beneficiaryList.remove(b);
        }

        public   void listEntities()
        {
            ArrayList<Entity> MATList=new ArrayList<Entity>();  //ftiaxnoume 2 nea arraylist gia na apothhkeusoume ta antikeimena
            ArrayList<Entity>  SERVList=new ArrayList<Entity>();
                for(var x:entityList)
                {
                    if(x.getISmaterial()==false)
                        SERVList.add(x);
                    else
                        MATList.add(x);
                }
                    System.out.println("Materials:");
                    for(var y:MATList)
                    System.out.println(y.toString());

                    System.out.println("Service:");
                    for(var z:SERVList)
                    System.out.println(z.toString());
                }

        public   ArrayList<Entity> getEntityList() //setters kai getters
        {
            return entityList;
        }

        public   ArrayList<Donator> getDonatorList()
        {
            return donatorList;
        }

        public   ArrayList<Beneficiary> getBenefList()
        {
            return beneficiaryList;
        }

        public   RequestDonationList returnCurDon()
        {
            return currentDonations;
        }

        public   void listBeneficiaries()
        {
            for(var x: beneficiaryList)
            {
                System.out.println(x.getName()+" "+x.getPhone());
            }
        }

        public   void listDonators()
        {
            for(var x: donatorList)
            {
                System.out.println(x.getName()+" "+x.getPhone());
            }
        }

        public   void addCurrentDon(RequestDonation e) throws ItemNotinListException, notInStockException, TooMuchException
        {
            currentDonations.add(e);
        }

        public   RequestDonation getCurrentDon(int id) throws ItemNotinListException
        {
            return currentDonations.get(id);
        }

        public   void removeCurrentDon(RequestDonation e)
        {
            currentDonations.remove(e);
        }

        public   void modifyCurrentDon(int j,double quantity) throws notInStockException, TooMuchException
        { 
            currentDonations.modify(j,quantity);
        }

        public   void resetCurrentDon()
        {
            currentDonations.reset();
        }

        public   void monitorDon()
        {
            currentDonations.monitor();
        }

        public   void listEntitiesAndQuantity()
        {
            ArrayList<Entity> MaterialList=new ArrayList<Entity>();
            ArrayList<Entity>  ServiceList=new ArrayList<Entity>();
            
                for(var x:entityList)
                {
                    if(x.getISmaterial()==false)
                        ServiceList.add(x);
                    else
                        MaterialList.add(x);
                }

                System.out.println("Για τα materials:");
                for(int i=0; i<MaterialList.size(); ++i)
                {
                    System.out.println(i+1+")"+MaterialList.get(i).getEntityInfo()+" ,η ποσότητα που ειναι διαθέσιμη:  "+EntityQuantityRatio(MaterialList).get(i));
                } //ektypwnontai ta materials kai oi antistoixes posothtes

                System.out.println("");

                System.out.println("Για τα services:");
                for(int i=0; i<ServiceList.size(); ++i)
                {
                    System.out.println(i+1+")"+ServiceList.get(i).getEntityInfo()+" ,η ποσότητα που είναι διαθέσιμη:  "+EntityQuantityRatio(ServiceList).get(i));
                }
                    
         }

                public   ArrayList<Double> EntityQuantityRatio(ArrayList<Entity> a)
                {
                    boolean entityExists = false;
                    EntityQuantity.clear();
                    for(var x:a)
                    {
                        for(var y: currentDonations.returnOrder())
                        {
                            entityExists = false;
                            if(x.entExists(y.getEntDon()))
                            {
                                entityExists = true;
                                EntityQuantity.add(y.getQuantity());
                                break;
                            }
                        }
                        if (!entityExists)
                            EntityQuantity.add(0.0); //an den yparxoun h posothta tous einai 0
                    }
                    return EntityQuantity;
                }

                public   void listMaterials()
                {
                    ArrayList<Entity> materials= new ArrayList<Entity>();
                        
                    for(var x:entityList)
                    {
                        if(x.getISmaterial()==true)
                            materials.add(x);
                    }

                    for(int i=0; i<materials.size(); ++i)
                        System.out.println(i+1+")"+materials.get(i).getEntityInfo()+" ,η ποσότητα που είναι διαθέσιμη:  "+EntityQuantityRatio(materials).get(i));          
                }

                public   void listServices()
                {
                    ArrayList<Entity> services= new ArrayList<Entity>();
                        
                    for(var x:entityList)
                    {
                        if(x.getISmaterial()==false)
                            services.add(x);
                    }

                    for(int i=0; i<services.size(); ++i)
                        System.out.println(i+1+")"+services.get(i).getEntityInfo()+" ,η ποσότητα που είναι διαθέσιμη:  "+EntityQuantityRatio(services).get(i));          
                }

                

                
    }





    

