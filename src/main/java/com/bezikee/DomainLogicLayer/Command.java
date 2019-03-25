package com.bezikee.DomainLogicLayer;


public abstract class Command
{


    public abstract void execute();

    public abstract boolean getStatus();

    public abstract String getMessage();
}