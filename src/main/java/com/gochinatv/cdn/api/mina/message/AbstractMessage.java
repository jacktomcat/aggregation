package com.gochinatv.cdn.api.mina.message;

public class AbstractMessage {
   
	
    static final long serialVersionUID = 1L;
    
    private int sequence;

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
    
}
