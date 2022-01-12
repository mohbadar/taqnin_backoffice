package com.nsia.officems._util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.nsia.officems._identity.authentication.group.Group;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class WorkflowJsonUtil {

    private static JSONObject jSONObject;

    // @Autowired
    // private UserServiceImpl userService;


    public JSONObject parse(String json) throws JSONException {
        jSONObject = new JSONObject(json);
        return jSONObject;
    }

    public  JSONObject getInitStep() throws JSONException {
        return jSONObject.getJSONArray("steps").getJSONObject(0);
    }

    //get current step + next authorized transitions
    public  JSONObject getCurrentStep(String instanceCurrentStep, String workflowJson, Collection<Group> groups) throws JSONException {

        if(instanceCurrentStep == null || instanceCurrentStep.trim().equals(""))
        {
            return getInitStep();
        }

        List<String> allGroups = groups.stream().map(Group::getName).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject(workflowJson);
        JSONArray steps = jsonObject.getJSONArray("steps");
        int index= getIndex(instanceCurrentStep);
        JSONObject currentStep = steps.getJSONObject(index);
            
        JSONArray allStepTransitions =currentStep.getJSONArray("transitions");
        List<String> transitions = convertTransitionsToListOfStrings(allStepTransitions);
        // System.out.println("Transitions=> "+ transitions.toString());
        JSONArray nextSteps = new JSONArray();

        for(String stepName: transitions){
            // System.out.println("Has Authority=> "+ hasAuthorizedGroup(stepName, steps, allGroups));
            if(hasAuthorizedGroup(stepName, steps, allGroups)){
                int stepIndex = getIndex(stepName);
                // System.out.println("NextStep => "+ getTranistion(allStepTransitions, stepName));
                nextSteps.put(getTranistion(allStepTransitions, stepName));
            }
        }

        // System.out.println("AllSteps=> "+ allStepTransitions.toString());
        return  currentStep.put("transitions", nextSteps);
    }

    public Object getTranistion(JSONArray allTransitions, String stepName) throws JSONException {
            int index = getTransitionIndex(allTransitions, stepName);
            return allTransitions.get(index);
    }


    public int getTransitionIndex(JSONArray allTransitions, String stepName) throws JSONException {
        int index= 0;
        for (int i=0; i < allTransitions.length(); i++) {
            JSONObject object = allTransitions.getJSONObject(i);
            String identifier = object.getString("toStep");
            if(stepName.equals(identifier)) {
                index=i;
            }
        }
        return index;
    }   
    //get next transitions
    public  JSONArray getNextTransitions(String name, String json) throws JSONException
    {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray steps = jsonObject.getJSONArray("steps");
        int index= getIndex(name, jsonObject);

        if(index == -1) {
            throw new IndexOutOfBoundsException();
        }

        JSONObject currentStep = steps.getJSONObject(index);
        JSONArray allStepTransitions =currentStep.getJSONArray("transitions");
        List<String> transitions = convertTransitionsToListOfStrings(allStepTransitions);
        return  currentStep.put("transitions", allStepTransitions).getJSONArray("transitions");
    }

    public JSONArray getSteps() throws JSONException
    {
        return jSONObject.getJSONArray("steps");
    }

    public  int getIndex(String name) throws JSONException
    {
        int index= 0;
        JSONArray steps = jSONObject.getJSONArray("steps");

        for (int i=0; i < steps.length(); i++)
        {
            JSONObject object = steps.getJSONObject(i);
            String identifier = object.getString("name");
            if(name.equals(identifier))
            {
                index=i;
            }
        }
        return index;
    }

    public  int getIndex(String name, JSONObject jsonObject) throws JSONException
    {
        int index= 0;
        JSONArray steps = jsonObject.getJSONArray("steps");

        for (int i=0; i< steps.length(); i++)
        {
            JSONObject object = steps.getJSONObject(i);
            String identifier = object.getString("name");
            if(name.equals(identifier))
            {
                index=i;
            }
        }
        return index;
    }


    private boolean hasAuthorizedGroup(String stepName, JSONArray steps, List<String> allGroups) throws JSONException
    {

        int index = getIndex(stepName);
        // System.out.println("Suth Index => :"+ index);
        JSONObject stepObj = steps.getJSONObject(index);
        JSONArray stepGroups = null;
        if(stepObj.has("authorizedGroups")) {
            stepGroups = stepObj.getJSONArray("authorizedGroups");
        } else {
            return true;
        }
        
        // System.out.println("Groups => :"+ stepGroups);
        List<String> authorizedGroups = convertAuthorizedGroupsToListOfStrings(stepGroups);
        // System.out.println("authorizedGroups => :"+ authorizedGroups.toString());
        boolean result = false;
        // for(String group: allGroups)
        // {
            for(String ag: authorizedGroups)
            {
                if(allGroups.contains(ag)){
                    result= true;
                }
            }
        // }
        return result;
    }

    private List<String> convertAuthorizedGroupsToListOfStrings(JSONArray authorizedGroups) throws JSONException
    {
        List<String> list = new ArrayList<String>();
        if(authorizedGroups != null) {
            for(int i = 0; i < authorizedGroups.length(); i++){
                list.add(authorizedGroups.getString(i));
            }
        }
        return list;
    }


    private List<String> convertTransitionsToListOfStrings(JSONArray transitions) throws JSONException
    {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < transitions.length(); i++){
            list.add(transitions.getJSONObject(i).getString("toStep"));
        }
        return list;
    }
}
