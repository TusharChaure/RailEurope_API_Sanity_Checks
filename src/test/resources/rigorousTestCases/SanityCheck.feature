@sanity
Feature: Pre-requisites before starting regression execution

  Scenario Outline: User login
    Given User should have valid login credentials <test>
    When Login with valid username and password
    Then Verify <test>, "<testCaseName>", "NA", "NA", 200 and schema from response body
    And Collect data from login response body <test>

    Examples: 
      | test | testCaseName       |
      |    1 | Admin login [root] |

  Scenario Outline: Search and select PTP journey
    Given Get origin station code "<origin>"
    Then Verify <test>, "<testCaseName>", "NA", "NA", 200 and schema from response body
    And Collect origin station code from response
    And Get departure station code "<departure>"
    Then Verify <test>, "<testCaseName>", "NA", "NA", 200 and schema from response body
    And Collect departure station code from response
    When Search PTP "<carrier>" journey
    Then Verify <test>, "<testCaseName>", "NA", "NA", 201 and schema from response body
    And Select "<carrier>" offer from returned journeys

    Examples: 
      | test | testCaseName           | origin                            | departure | carrier    |
      |    1 | Get OBB journey        | wien hbf                          | munich    | OBB_INTL   |
      |    2 | Get DB journey         | berlin                            | munich    | DBAHN      |
      |    3 | Get DBSNCF journey     | paris                             | stuttgart | DBSNCF     |
      |    4 | Get EUROSTAR journey   | london                            | paris     | EUROSTAR   |
      |    5 | Get RDG journey        | Edinburgh (Waverley, city centre) | london    | RDG        |
      |    6 | Get SNCB Journey       | Bruxelles/Brussels-Midi           | mons      | SNCB       |
      |    7 | Get SBB journey        | Zermatt                           | chur      | SBB        |
      |    8 | Get RENFE journey      | barcelona                         | madrid    | RENFE      |
      |    9 | Get RJET journey       | wien Hbf                          | gyor      | RJET       |
      |   10 | Get IRYO journey       | barcelona                         | madrid    | IRYO       |
      |   12 | Get OUIGOESP journey   | barcelona                         | madrid    | OUIGOESP   |
      |   13 | Get TRENITALIA journey | rome                              | milan     | TRENITALIA |
      |   14 | Get ITALO journey      | rome                              | milan     | ITALO      |

  Scenario Outline: Search and select pass
    Given Prepare request body for "<pass>" search pass operation
    When Get results for requested pass
    Then Verify <test>, "<testCaseName>", "NA", "NA", 201 and schema from response body
    And Select "<passCarrier>" pass from search response

    Examples: 
      | test | testCaseName        | pass | passCarrier |
      |    1 | Get Eurail Pass     | EU   | INTERRAIL   |
      |    2 | Get Swiss Pass      | CH   | INTERRAIL   |
      |    3 | Get Trenitalia Pass | IT   | INTERRAIL   |

  Scenario: Create booking of selected journeys
    Given Prepare request body selected journeys
    When Create booking for all carriers
    Then Verify 1, "Create booking", "NA", "NA", 201 and schema from response body
    And Collect require fields from response body

  Scenario: Verify booking details
    Given Get created bookings details
    Then Verify 1, "Get booking", "NA", "NA", 200 and schema from response body
    And Verify all booking count 13 PTP and 3 Passes

  Scenario Outline: Update traveler details
    Given Create update traveler request body with parameters "traveler-1", "ADULT", "true", 31, "tchaure@raileurope.com", "+919090909090", "+91", "MR", "Chaure", "Tushar", "1993-01-08", "FR", "T28372382738", "2030-12-12", "PASSPORT"
    When Update traveler details for each "<carrier>" journey
    Then Verify <test>, "<testCaseName>", "NA", "NA", 200 and schema from response body

    Examples: 
      | test | testCaseName                                   | carrier    |
      |    1 | Update traveler details for OBB journey        | OBB_INTL   |
      |    2 | Update traveler details for DBAHN journey      | DBAHN      |
      |    3 | Update traveler details for DBSNCF journey     | DBSNCF     |
      |    4 | Update traveler details for EUROSTAR journey   | EUROSTAR   |
      |    5 | Update traveler details for RDG journey        | RDG        |
      |    6 | Update traveler details for SNCB journey       | SNCB       |
      |    7 | Update traveler details for SBB journey        | SBB        |
      |    8 | Update traveler details for RENFE journey      | RENFE      |
      |    9 | Update traveler details for RJET journey       | RJET       |
      |   10 | Update traveler details for IRYO journey       | IRYO       |
      |   12 | Update traveler details for OUIGOESP journey   | OUIGOESP   |
      |   13 | Update traveler details for TRENITALIA journey | TRENITALIA |
      |   14 | Update traveler details for ITALO journey      | ITALO      |
      |   15 | Update traveler details for Europe pass        | EU         |
      |   16 | Update traveler details for Swiss pass         | CH         |
      |   17 | Update traveler details for Trenitalia pass    | IT         |

  Scenario: Prebook the journey
    Given Prepare request body to perform prebook operation
    When Prebook created bookings
    Then Verify 1, "Prebook booking", "NA", "NA", 200 and schema from response body
