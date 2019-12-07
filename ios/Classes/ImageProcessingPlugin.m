#import "ImageProcessingPlugin.h"
#if __has_include(<image_processing/image_processing-Swift.h>)
#import <image_processing/image_processing-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "image_processing-Swift.h"
#endif

@implementation ImageProcessingPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftImageProcessingPlugin registerWithRegistrar:registrar];
}
@end
