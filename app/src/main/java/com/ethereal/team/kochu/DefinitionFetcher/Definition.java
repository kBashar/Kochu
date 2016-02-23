package com.ethereal.team.kochu.DefinitionFetcher;

import java.util.List;

/**
 * @author Khyrul Bashar
 */
public class Definition
{
    private final String partsOfSpeech;
    private String definition;
    private String example;
    private List<String> synonyms;

    public Definition(String partsOfSpeech)
    {
        this.partsOfSpeech = partsOfSpeech;
    }

    public String getPartsOfSpeech()
    {
        return partsOfSpeech;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public String getExample()
    {
        return example;
    }

    public void setExample(String example)
    {
        this.example = example;
    }

    public List<String> getSynonyms()
    {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms)
    {
        this.synonyms = synonyms;
    }
}
