package project;

public  enum CourtType{
     COMMON,
     CONSTITUTIONAL_TRIBUNAL,
     SUPREME,
     NATIONAL_APPEAL_CHAMBER,
     PROVINCIAL_ADMINISTARTIVE_COURT,
     SUPREME_ADMINISTARTIVE_COURT;

     @Override
     public String toString(){
          switch(this){
               case COMMON: return "Common Court";
               case CONSTITUTIONAL_TRIBUNAL: return "Constitutional tribunal";
               case SUPREME: return "Supreme Court";
               case NATIONAL_APPEAL_CHAMBER: return "National Appeal Chamber";
               case PROVINCIAL_ADMINISTARTIVE_COURT: return "Provincial Administrative Court";
               default : return "Supreme Administrative Court";
          }
     }
}

