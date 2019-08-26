//
//  RCTConvert+Country.h
//  Fidel
//
//  Created by Corneliu on 22/03/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RCTConvert.h"
#import "Fidel-Swift.h"

#define FLCountryValues @{@"unitedKingdom" : @(FLCountryUnitedKingdom), @"ireland" : @(FLCountryIreland), @"unitedStates" : @(FLCountryUnitedStates), @"sweden" : @(FLCountrySweden), @"japan" : @(FLCountryJapan), @"canada" : @(FLCountryCanada), @"noDefault" : @(FLCountryNoDefault)}

@implementation RCTConvert (Country)

RCT_ENUM_CONVERTER(FLCountry, (FLCountryValues),
                   FLCountryNoDefault, integerValue);

@end
